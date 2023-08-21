
> **原创发布转载注明出处！**

> **ChatGpt Java SDK 可以看另外一个仓库：https://github.com/Grt1228/chatgpt-java**

# 📖 项目简介

**讯飞星火大模型的Java SDK。**

易使用、开箱即用、支持**异常**、**输出**、**会话结束**、**会话结束Token信息返回**等多种**自定义回调处理**。
# 📑 更新日志
- [x] 1.0.0   支持大模型chat接口，支持自定义回调处理。

# 🚀 快速开始
## 1、导入pom依赖
```
<dependency>
    <groupId>com.unfbx</groupId>
    <artifactId>SparkDesk-Java</artifactId>
    <version>1.0.0</version>
</dependency>
```
## 2、示例
```java
public class AppTest {
  @Test
  public void test() {
      //构建客户端
    SparkDeskClient sparkDeskClient = SparkDeskClient.builder()
            .host(SparkDesk.SPARK_API_HOST_WS_V2_1)
            .appid("****")
            .apiKey("************************")
            .apiSecret("************************")
            .build();
    //构建请求参数
    InHeader header = InHeader.builder().uid(UUID.randomUUID().toString().substring(0, 10)).appid("****").build();
    Parameter parameter = Parameter.builder().chat(Chat.builder().domain("generalv2").maxTokens(2048).temperature(0.3).build()).build();
    List<Text> text = new ArrayList<>();
    text.add(Text.builder().role(Text.Role.USER.getName()).content("使用md文档格式写出一个三行三列的表格，表头包含：姓名，性别，爱好。数据随机即可。").build());
    InPayload payload = InPayload.builder().message(Message.builder().text(text).build()).build();
    AIChatRequest aiChatRequest = AIChatRequest.builder().header(header).parameter(parameter).payload(payload).build();
    
    //发送请求
    sparkDeskClient.chat(new ChatListener(aiChatRequest) {
      //异常回调
      @SneakyThrows
      @Override
      public void onChatError(AIChatResponse aiChatResponse) {
        log.warn(String.valueOf(aiChatResponse));
      }
      //输出回调
      @Override
      public void onChatOutput(AIChatResponse aiChatResponse) {
        System.out.println("content: " + aiChatResponse);
      }
      //会话结束回调
      @Override
      public void onChatEnd() {
        System.out.println("当前会话结束了");
      }
      //会话结束 获取token使用信息回调
      @Override
      public void onChatToken(Usage usage) {
        System.out.println("token 信息：" + usage);
      }
    });

    CountDownLatch countDownLatch = new CountDownLatch(1);
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

```

# ☕ 打赏
如果项目对你有帮助，可以选择打赏我。

<img width="180" alt="微信截图_20230405222411" src="https://user-images.githubusercontent.com/27008803/230111508-3179cf30-e128-4b2e-9645-157266c491ce.png">  <img width="164" alt="微信截图_20230405222357" src="https://user-images.githubusercontent.com/27008803/230111525-322f5036-d06d-46bb-94d1-db8ce9ed2adf.png">

