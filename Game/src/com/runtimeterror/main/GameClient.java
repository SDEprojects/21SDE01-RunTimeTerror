package com.runtimeterror.main;

import com.runtimeterror.controller.GameInterface;
import com.runtimeterror.stat.Item;
import com.runtimeterror.stat.LoadRoomData;
import com.runtimeterror.stat.Player;
import com.runtimeterror.stat.Rooms;
import com.runtimeterror.textparser.InputData;
import com.runtimeterror.textparser.Parser;
import static com.runtimeterror.textparser.Verbs.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameClient implements GameInterface {
    HashMap<String, Rooms> rooms;
    Player player;

    GameClient(){
        try {
            rooms = LoadRoomData.load();
        }
        catch (Exception e){
            System.out.printf("Failed to load the game files:");
            System.out.println(e.getMessage());
        }
        player = new Player(rooms.get("MasterBathroom"));
    }

    @Override
    public String getRoomText() {
        String result = "Location:\n";
        Rooms room = player.getCurrRoom();
        result += room.getRoomName();
        result += "\n\n";
        result += room.getRoomDescription();

        return result;
    }

    @Override
    public String getPLayerInventory() {
        List<Item> inv = player.getInventory();
        List<String> invString =  new ArrayList<>();
        for (Item item : inv){
            invString.add(item.getName());
        }
        String result = "Inventory: \n" + String.join(", ", invString);
        return result;
    }

    @Override
    public String submitPlayerString(String inputString) {
        String result = "";
        InputData parsedInput = Parser.parseInput(inputString);
        // check if the player requested a get command.
        if  (parsedInput == null){
            result = "I don't understand \"" + inputString + "\"";
        }
        else if (GET.equals(parsedInput.getVerbType())){
            System.out.println("process get");
            result = "GET commands not yet implemented";
        }
        // check if the player requested a move command.
        else if (GO.equals(parsedInput.getVerbType())) {
            result = processMove(parsedInput);
        }
        // check if the player requested a use command.
        else if (USE.equals(parsedInput.getVerbType())) {
            result = "USE commands not yet implemented";
        }
        // check if the player requested a look command.
        else if (LOOK.equals(parsedInput.getVerbType())) {
            result = "LOOK commands not yet implemented";
        }
        // check if the player requested a hide command.
        else if (HIDE.equals(parsedInput.getVerbType())) {
            result = "HIDE commands not yet implemented";
        }
        return result;
    }

    private String processMove(InputData data){
        return player.changeRoom(data.getNoun());
    }
}