/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.UUID;
/**
 *
 * @author Avi
 * Relative to Server
 */
public class ChatProtocol implements ChatProtocolInterface{


    public void ProccessOutput(String message){//get rid of this maybe
        String messageSplit[] = message.split(" ");
        String content = messageSplit[1];
        String protocol = messageSplit[0];
        String output = null;
        switch(protocol){
            case "/000/":
                System.out.println(content);
                break; //message person
            case "/001/":
                break; //find person
            case "/002/":
                break; //person found
            case "/003/":
                break; //send friend request
            case "/004/":
                break; //accept friend request
            case "/005/":
                break; //decline friend request
            case "/006/":
                System.out.println("SHOULD NOT GET HERE: Accepted Friend Request");
                break;//Accepted Friend Request
            case "/007":
                break;
        }
    }

    @Override
    public void ProccessInput(String message){
        String[] spliceCommandCode = message.split(" ");
        String commandCode = spliceCommandCode[0];
        String content = spliceCommandCode[1];
        switch(commandCode){
            case "/000/":
                System.out.println(content);
                break; //message person
            case "/001/":
                break; //find person
            case "/002/":
                break; //person found
            case "/003/":
                break; //send friend request
            case "/004/":
                break; //accept friend request
            case "/005/":
                break; //decline friend request
            case "/006/":
                //ADD USER TO FRIENDSLIST
                break;//Accepted Friend Request
            case "/007":
                break;
        }
    }
}
