# Spring Boot WebDAV Demo

## 介绍

本项目是一个基于 Spring Boot 构建的 WebDAV 中转服务器示例，专门设计来通过 HTTP 请求中转操作到 WebDAV 网盘。这种中转方式使得用户可以利用简单的 HTTP 请求来管理存储在 WebDAV 网盘上的文件，例如上传、下载和删除文件。

## 功能特点

- **HTTP 到 WebDAV 中转**：通过 HTTP 请求直接中转到后端的 WebDAV 网盘，无需直接与 WebDAV 服务器交互。
- **轻量级服务器**：使用 Spring Boot 构建，便于集成和快速部署。
- **文件管理功能**：支持通过 HTTP 接口管理文件，包括上传、下载、浏览和删除。

## 快速开始

### 先决条件

请确保你的系统已安装以下软件：

- JDK 11 或更高版本
- Maven 3.6 或更高版本

### 安装和运行

1. 克隆仓库：

```bash
git clone https://github.com/GeekyWizKid/springboot-webdav-demo.git
cd springboot-webdav-demo
```

2. 使用 Maven 构建项目：

```bash
mvn clean install
```

3. 运行应用程序：

```bash
mvn spring-boot:run
```

应用程序将默认运行在 `http://localhost:8080/`

## 使用示例

通过访问 `http://localhost:8080/` 或使用任何 HTTP 客户端，你可以开始通过 HTTP 请求来管理存储在 WebDAV 网盘上的文件。

## 开发和贡献

我们欢迎任何形式的贡献，如下是贡献指南：

1. Fork 仓库。
2. 创建你的功能分支 (`git checkout -b feature/AmazingFeature`)。
3. 提交你的更改 (`git commit -m 'Add some AmazingFeature'`)。
4. 推送到分支 (`git push origin feature/AmazingFeature`)。
5. 发起 Pull Request。

## 许可证

本项目采用 MIT 许可证。详细信息请参阅 `LICENSE` 文件。

## 联系方式

- GitHub [@GeekyWizKid](https://github.com/GeekyWizKid)

---
