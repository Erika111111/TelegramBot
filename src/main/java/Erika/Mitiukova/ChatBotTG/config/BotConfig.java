package Erika.Mitiukova.ChatBotTG.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.username}")
    String botName;
    @Value("${bot.token}")
    String token;

    public BotConfig(String botName, String token) {
        this.botName = botName;
        this.token = token;
    }

    public BotConfig() {
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "BotConfig{" +
                "botName='" + botName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
