package Erika.Mitiukova.ChatBotTG.service;

import Erika.Mitiukova.ChatBotTG.config.BotConfig;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {

        this.botConfig = botConfig;
    }

public void onUpdateReceived(Update update){
        if (update.hasMessage() && update.getMessage().getNewChatMembers() != null){
            update.getMessage().getNewChatMembers().forEach(user -> {
                String welcomeMessage = "Привет, " + user.getFirstName() + "! Добро пожаловать на наш канал!\n\n"
                        + "Не забудь подписаться на наши соцсети: \n"
                        + "Instagram: @erika_po\n"
                        + "VK: @id7927385 \n"
                        + "ты можешь вызвать команду 'help' и посмотреть какие функции тебе доступны";
                sendMessage(update.getMessage().getChatId(), welcomeMessage);

            });
        }

            if (update.hasMessage() && update.getMessage().hasText()){

                String message = update.getMessage().getText();

            if (message.equalsIgnoreCase("Привет")){
                String resMessageGD = "Хорошего дня! И всего самого лучшего!";
                sendMessage(update.getMessage().getChatId(),resMessageGD);
            }
            if (message.equalsIgnoreCase("Пока")){
                String resMessageGB = "До свидания! Всего вам хорошего!";
                sendMessage(update.getMessage().getChatId(),resMessageGB);
            }
            if (message.equalsIgnoreCase("Help")){
                String helpMessage = "Всегда готова помочь! Вот список доступных команд: \n"
                        + "Привет - Приветствие \n"
                        + "Пока - отвечет взаимностью\n"
                        + "Время - отобразит реальное московсокое время";
                sendMessage(update.getMessage().getChatId(), helpMessage);
            }else if (message.equalsIgnoreCase("Время")){
                LocalDateTime moscowTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String currentTime = moscowTime.format(formatter);
                String timeMessage = "Текущее московское время " + currentTime;

                sendMessage(update.getMessage().getChatId(), timeMessage);
            }

        }
}

    /**
     * метод отправки сообщения пользователю
     * @param chatId
     * @param textToSend
     */
    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
