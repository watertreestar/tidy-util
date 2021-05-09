package com.github.watertreestar.klass;

import com.github.watertreestar.StringUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 扫描类路径(classpath)下指定包下的类
 */
public class Scanner {
    private String basePackage;

    private ClassLoader classLoader;

    public Scanner(String basePackage,ClassLoader classLoader){
        this.classLoader = classLoader;
        this.basePackage = basePackage;

    }

    public Scanner(String basePackage){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        this.classLoader = loader;
        this.basePackage = basePackage;
    }

    public List<Class<?>> scan(){
        List<String> resources = new ArrayList<>();
        Arrays.asList(this.basePackage.split(",")).stream().forEach(pkg -> {

        });
        return null;
    }

    private void doScan(String pkgName,List<String> resources) throws IOException {
        String path = StringUtil.packageToPath(pkgName);
        Enumeration<URL> urls = classLoader.getResources(path);
        while (urls.hasMoreElements()) {
            URL u = urls.nextElement();
            ResourceType type = determineType(u);
            List<String> list = new ArrayList<>();
            switch (type) {
                case JAR:
                    list = scanJar(u.getPath());
                    break;

                case FILE:
                    list = scanFile(u.getPath(), pkgName);
                    break;
            }
            resources.addAll(list);
        }
    }

    private ResourceType determineType(URL url) {
        if (url.getProtocol().equals(ResourceType.FILE.getTypeString())) {
            return ResourceType.FILE;
        }

        if (url.getProtocol().equals(ResourceType.JAR.getTypeString())) {
            return ResourceType.JAR;
        }

        throw new IllegalArgumentException("不支持该类型:" + url.getProtocol());
    }

    /**
     * 扫描JAR文件
     * @param path
     * @return
     * @throws IOException
     */
    private List<String> scanJar(String path) throws IOException {
        JarFile jar = new JarFile(path);

        List<String> classNameList = new ArrayList<>(20);

        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();

            if( (name.startsWith(path)) && (name.endsWith(ResourceType.CLASS_FILE.getTypeString())) ) {
                name = StringUtil.trimSuffix(name);
                name = StringUtil.pathToPackage(name);

                classNameList.add(name);
            }
        }

        return classNameList;
    }

    /**
     * 扫描文件目录下的类
     * @param path
     * @return
     */
    private List<String> scanFile(String path, String basePkg) {
        File f = new File(path);

        List<String> classNameList = new ArrayList<>(10);

        // 得到目录下所有文件(目录)
        File[] files = f.listFiles();
        if (null != files) {
            int LEN = files.length;

            for (int ix = 0 ; ix < LEN ; ++ix) {
                File file = files[ix];

                // 判断是否还是一个目录
                if (file.isDirectory()) {
                    // 递归遍历目录
                    List<String> list = scanFile(file.getAbsolutePath(), StringUtil.concat(basePkg, ".", file.getName()));
                    classNameList.addAll(list);

                } else if (file.getName().endsWith(ResourceType.CLASS_FILE.getTypeString())) {
                    // 如果是以.class结尾
                    String className = StringUtil.trimSuffix(file.getName());
                    // 如果类名中有"$"不计算在内
                    if (-1 != className.lastIndexOf("$")) {
                        continue;
                    }
                    // 命中
                    String result = StringUtil.concat(basePkg, ".", className);
                    classNameList.add(result);
                }
            }
        }

        return classNameList;
    }
}
