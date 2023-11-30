package com.unfbx;

import com.unfbx.sparkdesk.SparkDeskClient;
import com.unfbx.sparkdesk.constant.SparkDesk;
import com.unfbx.sparkdesk.entity.*;
import com.unfbx.sparkdesk.listener.ChatListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
@Slf4j
public class AppTest {

    @Test
    public void test() {
        SparkDeskClient sparkDeskClient = SparkDeskClient.builder()
                .host(SparkDesk.SPARK_API_HOST_WS_V3_1)
                .appid("****")
                .apiKey("************************")
                .apiSecret("************************")
                .build();
        InHeader header = InHeader.builder().uid(UUID.randomUUID().toString().substring(0, 10)).appid(sparkDeskClient.getAppid()).build();
        Parameter parameter = Parameter.builder().chat(Chat.builder().domain("generalv3").maxTokens(2048).temperature(0.3).build()).build();
        List<Text> text = new ArrayList<>();
        text.add(Text.builder().role(Text.Role.USER.getName()).content("北京天气").build());
        InPayload payload = InPayload.builder().message(Message.builder().text(text).build()).functions(buildFunction()).build();
        AIChatRequest aiChatRequest = AIChatRequest.builder().header(header).parameter(parameter).payload(payload).build();

        sparkDeskClient.chat(new ChatListener(aiChatRequest) {
            @SneakyThrows
            @Override
            public void onChatError(AIChatResponse aiChatResponse) {
                log.warn(String.valueOf(aiChatResponse));
            }

            @Override
            public void onChatOutput(AIChatResponse aiChatResponse) {
                System.out.println("content: " + aiChatResponse);
            }

            @Override
            public void onChatEnd() {
                System.out.println("当前会话结束了");
            }

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

    private Function buildFunction() {
        Function function = new Function();
        Function.Text text1 = new Function.Text();
        function.setText(Collections.singletonList(text1));

        text1.setName("天气查询");
        text1.setDescription("天气插件可以提供天气相关信息。你可以提供指定的地点信息、指定的时间点或者时间段信息，来检索诗词库，精准检索到天气信息。");

        Function.Parameter parameter = new Function.Parameter();
        text1.setParameters(parameter);

        parameter.setType("object");

        Function.Properties location = Function.Properties.builder().type("string").description("地点，比如北京。").build();
        Function.Properties date = Function.Properties.builder().type("string").description("日期。").build();

        Map<String, Function.Properties> map = new HashMap<>();
        map.put("location", location);
        map.put("date", date);
        parameter.setProperties(map);

        parameter.setRequired(Collections.singletonList("location"));
        return function;
    }
}
