# tidy util

🍎 A Java library for common tools

![](https://img.shields.io/badge/JDK-8%2B-brightgreen)
![](https://github.com/watertreestar/tidy-util/workflows/ci/badge.svg)

## 🛠️ 安装

该工具库已经上传到重要仓库中，maven坐标

```xml

```

## 🎉 前言

该项目主要包含了平时经常使用到的工具，都是用了最简单的实现，没有其它依赖。有了它，就不必在每个项目中都创建重复的工具类

## 🏢 模块介绍

- array 数组相关
  - ArrayUtil 数组工具类

- executor 线程池
  - TidyExecutor 线程池，具有重试，超时，同步，异步执行的能力
  - AbstractTask 在TidyExecutor中执行的抽象任务

- functional 函数式编程
  - async
    - AsyncUtil 简单的异步执行，支持函数式

- http HTTP相关
  - HttpUtil HTTP请求，支持同步和伪异步
  - aync
    - AsyncHttpClient 支持异步请求的工具，基于NIO

- io 输入输出相关

- klass 类处理相关
  - Scanner 类扫描

- retry 重试相关

- text 文本处理
  - TextUtil 文本处理

- token 令牌相关
  - Token token的加密和解密

- CollectionUtil Collection相关

- CryptoUtil 加密解密

- HashCodeUtil hashcode获取

- RetryUtil 简单的支持重试工具

- StringUtil 字符串工具

- SystemClock 全局系统时钟

## 📄 开源协议

tidy-util is licensed under the term of the Apache License