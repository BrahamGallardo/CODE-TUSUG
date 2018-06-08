/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomException;

/**
 *
 * @author Alekhius
 */
public class InvalidFormatException extends Exception{
    public static int ERROR_RFC;
    int code_error;
    public InvalidFormatException(String msg,int code){
        super(msg);
        code_error = code;
    }
    
}
