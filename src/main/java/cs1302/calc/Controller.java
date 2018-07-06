
package cs1302.calc;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
 * Provides a link between the fxml and the java components of the calculator.
 *
 * @author Devin Everage and Daniel Tomlinson
 */
public class Controller implements Initializable {
    
    private boolean isRecursive;
    private long result;
    private int current;
    private String polish;
    @FXML
    private Button bit00, bit01, bit02, bit03, bit04, bit05, bit06, bit07, bit08, bit09, bit10, bit11, bit12, bit13, bit14, bit15, bit16, bit17, bit18, bit19, bit20, bit21, bit22, bit23, bit24, bit25, bit26, bit27, bit28, bit29, bit30, bit31;
    @FXML
    private TextField displayField;
    
    @FXML
    private TextField answerField;
    

    public void initialize(URL rl, ResourceBundle rb) {
    
    }
    
    /**
     * Adds whatever digit is pressed to the end of the operations display text field.
     *
     * @param event the event created by the button being pressed
     */
    @FXML
    private void handleDigitAction(ActionEvent event) {
        String digit = ((Button) event.getSource()).getText();
        String beg = displayField.getText();
        String end = beg + digit;
        displayField.setText(end);
        current = Integer.parseInt(digit);
    }
    
    /**
     * Uses the content of the display text field to get the polish notation, calculates the answer, and sets the answer text field to that value. Uses either Recursive or Iterative notation.
     *
     * @param event the event created by the button being pressed
     */    
    @FXML
    private void handleEqualOperation(ActionEvent event) {
        String[] holder;
        polish = displayField.getText();
        //System.out.println(polish); debug line
        
        for (int n = 0; n < polish.length() - 2; n++) {
          if (polish.charAt(n) == '>' || polish.charAt(n) == '<') {
            polish = polish.substring(0, n+1) + polish.substring(n+2);
          }  
        }
        
        String infix[] = polish.split(" ");
        String postfix[] = ReversePolishNotation.infixToPostfix(infix);
        if(isRecursive == true){
          Math recursiveMath = new RecursiveMath();
          result = ReversePolishNotation.evaluate(recursiveMath, postfix);
          changeButtons();
          answerField.setText(Long.toString(result));
      
        }
        else{
          Math iterativeMath = new IterativeMath();
          result = ReversePolishNotation.evaluate(iterativeMath, postfix);
          //System.out.println(result); debug line
          changeButtons();
          answerField.setText(Long.toString(result));
 
        }
    
    }
    
    /**
     * Adds whatever operation is pressed to the end of the operations display text field.
     *
     * @param event the event created by the button being pressed
     */
    @FXML
    private void handleOperation(ActionEvent event){
        String currentText = displayField.getText();
        char lastChar = currentText.charAt(currentText.length() - 1);
        if(lastChar == ' '){
          lastChar = currentText.charAt(currentText.length() - 2);
        }
        if(lastChar == '+' || lastChar == '-' || lastChar == '^' || lastChar == '!' || lastChar == '/' || lastChar == '*' || lastChar == '>' || lastChar == '<'){
          
          displayField.setText(currentText + ((Button) event.getSource()).getText() + " ");
        }else {
        
        displayField.setText(currentText + " " + ((Button) event.getSource()).getText() + " ");
        
        }
    }
    
    /**
     * Empties the operations display text field and answer text field. Sets all binary buttons to 0.
     *
     * @param event the event created by the button being pressed
     */
    @FXML
    private void handleClearOperation(ActionEvent event) {
        displayField.setText("");
        answerField.setText("");
        polish = "";
        changeButtonsToZero();
    
    }
    
    /**
     * Removes the last operation or number to be entered into the operations display text field.
     *
     * @param event the event created by the button being pressed
     */
    @FXML
    private void handleBackOperation(ActionEvent event) {
        String beg = displayField.getText();
        char lastChar = beg.charAt(beg.length()-1);
        if(lastChar == ' '){
          lastChar = beg.charAt(beg.length()- 2);
        }
        String mid;
        if(lastChar == '+' || lastChar == '-' || lastChar == '^' || lastChar == '!' || lastChar == '/' || lastChar == '*'){
          mid = beg.substring(0,beg.length()-2);
        }else if(lastChar == '>' || lastChar == '<'){
          mid = beg.substring(0,beg.length()-3);
        }else{
          mid = beg.substring(0,beg.length()-1);
        }
        displayField.setText(mid);
    }
    
    /**
     * Switches the notation in use from Recursive to Iterative or vice versa, depending on which is in use.
     *
     * @param event the event created by the button being pressed
     */
    @FXML
    private void handleUseRecursion(ActionEvent event) {
        //System.out.println(((Button) event.getSource()).getText()); debug line
        String state = ((Button) event.getSource()).getText();
        if(state.equals("Use Recursive")){
        isRecursive = false;
        ((Button) event.getSource()).setText("Use Iterative");
      }else{
        isRecursive = true;
        ((Button) event.getSource()).setText("Use Recursive");
      }
    }
    
    /**
     * Toggles whatever binary button is pressed to the opposite value, and recalculates and redisplays the answer text field.
     *
     * @param event the event created by the button being pressed
     */
    @FXML
    private void handleBinaryButton(ActionEvent event){
      String buttonText;
      buttonText = ((Button) event.getSource()).getText();
      if(buttonText.equals("0")){
        ((Button) event.getSource()).setText("1");
      }else{
        ((Button) event.getSource()).setText("0");
      }
      buttonText = fromBinary();
      try {
      result = Integer.parseInt(buttonText,2);
      } catch (NumberFormatException e) {
        System.out.println("Gotcha");
      }
      //System.out.println(buttonText); debug line
      //System.out.println(result);      debug line
      answerField.setText(Long.toString(result));
      
    
    }
    
    /**
     * Converts the result of the math expression to its binary equivalent
     *
     * @param ans the result of the math expression being calculated
     * @return the binary equivalent of the result
     */
    private String[] toBinary(int ans){
        
        String rep = Integer.toBinaryString(ans);
        String[] binary = new String[rep.length()];
        for(int u =0; u < rep.length(); u++){
          binary[u] = Character.toString(rep.charAt(u));
          //System.out.print(binary[u]); debug line
        }
        //System.out.println("rep: " + rep); debug line
        return binary;
    }
    
    /**
     * Gets the numerical equivalent of the binary
     *
     * @return the numerical value of the binary
     */
    private String fromBinary(){
        String form="";
        String[] holdem = new String[32];
        
        holdem[31] = bit00.getText();
        holdem[30] = bit01.getText();
        holdem[29] = bit02.getText();
        holdem[28] = bit03.getText();
        holdem[27] = bit04.getText();
        holdem[26] = bit05.getText();
        holdem[25] = bit06.getText();
        holdem[24] = bit07.getText();
        holdem[23] = bit08.getText();
        holdem[22] = bit09.getText();
        holdem[21] = bit10.getText();
        holdem[20] = bit11.getText();
        holdem[19] = bit12.getText();
        holdem[18] = bit13.getText();
        holdem[17] = bit14.getText();
        holdem[16] = bit15.getText();
        holdem[15] = bit16.getText();
        holdem[14] = bit17.getText();
        holdem[13] = bit18.getText();
        holdem[12] = bit19.getText();
        holdem[11] = bit20.getText();
        holdem[10] = bit21.getText();
        holdem[9] = bit22.getText();
        holdem[8] = bit23.getText();
        holdem[7] = bit24.getText();
        holdem[6] = bit25.getText();
        holdem[5] = bit26.getText();
        holdem[4] = bit27.getText();
        holdem[3] = bit28.getText();
        holdem[2] = bit29.getText();
        holdem[1] = bit30.getText();
        holdem[0] = bit31.getText();
        for(int p = 0; p < holdem.length; p++)
        {
          form = form + holdem[p];
        }
        //System.out.println(form); debug line
        return form;
    }
    
    /**
     * Sets the buttons to the binary equivalent of the result of the mathematical expression.
     */
    private void changeButtons(){
        String[] temp = toBinary((int)result);
        int place = temp.length-1;
 
          if(place >= 0){
          bit00.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit01.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit02.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit03.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit04.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit05.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit06.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit07.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit08.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit09.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit10.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit11.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit12.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit13.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit14.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit15.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit16.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit17.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit18.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit19.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit20.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit21.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit22.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit23.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit24.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit25.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit26.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit27.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit28.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit29.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit30.setText(temp[place]);
          place--;}
          if(place >= 0){
          bit31.setText(temp[place]);}
    
    }
    
    /**
     * Sets all binary buttons to 0.
     */
    private void changeButtonsToZero() {
        String s = "0";
      
        bit00.setText(s);
        bit01.setText(s);
        bit02.setText(s);
        bit03.setText(s);
        bit04.setText(s);
        bit05.setText(s);
        bit06.setText(s);
        bit07.setText(s);
        bit08.setText(s);
        bit09.setText(s);
        bit10.setText(s);
        bit11.setText(s);
        bit12.setText(s);
        bit13.setText(s);
        bit14.setText(s);
        bit15.setText(s);
        bit16.setText(s);
        bit17.setText(s);
        bit18.setText(s);
        bit19.setText(s);
        bit20.setText(s);
        bit21.setText(s);
        bit22.setText(s);
        bit23.setText(s);
        bit24.setText(s);
        bit25.setText(s);
        bit26.setText(s);
        bit27.setText(s);
        bit28.setText(s);
        bit29.setText(s);
        bit30.setText(s);
        bit31.setText(s);
    }

}