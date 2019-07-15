
import org.checkerframework.checker.units.qual.K;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try{
            telegramBotsApi.registerBot(new Bot());

        }catch(TelegramApiException e){
            e.printStackTrace();

        }
    }


    public void sendMsg(Message message, String text){

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{

            setButtons(sendMessage);
            execute(sendMessage);

        } catch (TelegramApiException e){
            e.printStackTrace();
        }

    }

    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendMsg(message, "Чем могу помочь?");
                    break;

                case "/setting":
                    sendMsg(message, "Что будем настраивать?");
                    break;


                    default:
                            try {
                                sendMsg(message, Weather.getWeather(message.getText(), model));
                            } catch (IOException e) {
                                sendMsg(message, "Такой город не найден((");
                            }

            }
        }

    }


    public void  setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowlist = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/setting"));

        keyboardRowlist.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowlist);




    }


    public String getBotUsername() {
        return "MyTestFirstBot";
    }

    public String getBotToken() {
        return "885320942:AAFlOuK7lYmX2xPlH5SiJZpHzxP6Ljn1TYI";
    }
}
