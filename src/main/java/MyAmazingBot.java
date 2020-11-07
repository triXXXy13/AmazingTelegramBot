import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class MyAmazingBot extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {
         if(update.hasMessage() && update.getMessage().hasText()){
             String message_text = update.getMessage().getText();
             long chat_id = update.getMessage().getChatId();
             SendMessage message = new SendMessage()
                     .setChatId(chat_id)
                     .setText(message_text);
             try{
                 execute(message);
             } catch (TelegramApiException e){
                 e.printStackTrace();
             }
         }else if (update.hasMessage() && update.getMessage().hasPhoto()) {
    // Message contains photo
    // Set variables
    long chat_id = update.getMessage().getChatId();

    // Array with photo objects with different sizes
    // We will get the biggest photo from that array
    List<PhotoSize> photos = update.getMessage().getPhoto();
    // Know file_id
    String f_id = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
    // Know photo width
    int f_width = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getWidth();
    // Know photo height
    int f_height = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getHeight();
    // Set photo caption
    String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
    SendPhoto msg = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto(f_id)
                    .setCaption(caption);
    try {
        sendPhoto(msg); // Call method to send the photo with caption
    } catch (TelegramApiException e) {
        e.printStackTrace();
    }
}
    }

    public String getBotUsername() {
        return "TheByteFoxBot";
    }

    public String getBotToken() {
        return "1376500093:AAFNVrrpsqLXOb5zmhvSQGl33fGK_7COxZE";
    }
}
