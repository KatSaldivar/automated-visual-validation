package data;

import org.openqa.selenium.By;

public enum BookData {
    AGILE_TESTING("pid3");

    String id;
    BookData(String id){
        this.id = id;
    }

    public By getLocator(){
        return By.id(id);
    }
}
