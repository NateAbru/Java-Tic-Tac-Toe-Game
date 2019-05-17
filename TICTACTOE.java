/*
 * This is a TicTacToe Game
 * using Java FX with the option
 * to play against another player or a CPU
 * Author: Nathaniel Abreu
 */
import javafx.event.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.Random;
public class TICTACTOE extends Application 
{
   int counter = 1, turn = 1, switchTurns = 1;
   int[][] array = new int[3][3];
   boolean cpuWinCheck = false, playCPU = false, gameOver = false;
   Random rDraw = new Random();
   Button[][] btn = new Button[3][3];
   public void start(Stage primaryStage)
   {
      for(int r = 0; r < 3; r++)
      {
         for(int c = 0; c < 3; c++)
    	 {
    	    counter++;
    		array[r][c] = 3 + counter;
    	 }
      }
      for(int r2 = 0; r2 < 3; r2++)
      {
         int num = r2;
    	 for(int c2 = 0; c2 < 3; c2++)
    	 {
    	    int num2 = c2;
    		btn[r2][c2] = new Button("-");
    	    btn[r2][c2].setPrefHeight(140);
    		btn[r2][c2].setPrefWidth(140);
    		btn[r2][c2].setFont(Font.font("Verdana", 40));
    		btn[r2][c2].setOnAction(new EventHandler<ActionEvent>()
    	    {
    	       public void handle(ActionEvent e) 
    	       {
    	          if(((turn % 2 == 1) && (((Button)e.getSource()).getText().equals("-")) && (switchTurns % 2 == 1) && (gameOver == false)) || 
    	             ((switchTurns % 2 == 0) && (turn % 2 == 0) && ((Button)e.getSource()).getText().equals("-") && gameOver == false))
    	          {
    	             ((Button)e.getSource()).setText("X");
    	             ((Button)e.getSource()).setFont(Font.font("Verdana", 40));
    	             array[num][num2] = 1;
    	             turn++;
    	             if(!winnerCheck()) smartCPU();
    	          }
    	          else if(((turn % 2 == 0) && (((Button)e.getSource()).getText().equals("-")) && (switchTurns % 2 == 1) && (gameOver == false)) ||
    	            	  ((switchTurns % 2 == 0) && (turn % 2 == 1) && (((Button)e.getSource()).getText().equals("-")) && (gameOver == false)))
    	          {
    	             ((Button)e.getSource()).setText("O");
    	             ((Button)e.getSource()).setFont(Font.font("Verdana", 40));
    	             array[num][num2] = 2;
    	             turn++;
    	          }
    	          if(winnerCheck() == true && ((Button)e.getSource()).getText().equals("X") && cpuWinCheck == false) 
    	          {
    	        	 gameOver = true;
    	             Alert a1 = new Alert(Alert.AlertType.INFORMATION);
    	             a1.setTitle("GAME OVER");
    	             a1.setContentText("PLAYER 1 WINS!");
    	             a1.setHeaderText(null);;
    	             a1.showAndWait();
    	          }
    	          else if(winnerCheck() == true && ((Button)e.getSource()).getText().equals("O")) 
    	          {
    	        	 gameOver = true;
    	             Alert a1 = new Alert(Alert.AlertType.INFORMATION);
    	             a1.setTitle("GAME OVER");
    	             a1.setContentText("PLAYER 2 WINS!");
    	             a1.setHeaderText(null);;
    	             a1.showAndWait();
    	          }
    	          else 
    	          {
    	             if(!btn[0][0].getText().equals("-") && !btn[0][1].getText().equals("-") &&
    	                !btn[0][2].getText().equals("-") && !btn[1][0].getText().equals("-") &&
    	                !btn[1][1].getText().equals("-") && !btn[1][2].getText().equals("-") &&
    	                !btn[2][0].getText().equals("-") && !btn[2][1].getText().equals("-") &&
    	                !btn[2][2].getText().equals("-")) 
    	             {
    	            	gameOver = true;
    	                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
    	                a1.setTitle("GAME OVER");
    	                a1.setContentText("DRAW!");
    	                a1.setHeaderText(null);;
    	                a1.showAndWait();
    	             }
    	          }
    	       }
    	    });
    	 }
      }
      BorderPane root = new BorderPane();
      root.setRight(getVBox());
      GridPane grid = new GridPane();
      grid.setPadding(new Insets(14, 14, 14, 14));
      grid.setPrefHeight(420);
      grid.setPrefWidth(420);
      grid.setAlignment(Pos.BASELINE_LEFT);
      grid.add(btn[0][0], 0, 0);
      grid.add(btn[0][1], 0, 1);
      grid.add(btn[0][2], 0, 2);
      grid.add(btn[1][0], 1, 0);
      grid.add(btn[1][1], 1, 1);
      grid.add(btn[1][2], 1, 2);
      grid.add(btn[2][0], 2, 0);
      grid.add(btn[2][1], 2, 1);
      grid.add(btn[2][2], 2, 2);
      root.setLeft(grid);
      
      Scene myScene = new Scene(root, 650, 600);
      primaryStage.setTitle("TIC TAC TOE");
      primaryStage.setScene(myScene);
      primaryStage.show();
      Alert a1 = new Alert(Alert.AlertType.INFORMATION);
	  a1.setTitle("GAME START");
	  a1.setContentText("Player 1 is X. Player 2 is O. Player 1 has first move! Hit Switch Player Turn to make Player 2 go first! Begin!!!");
	  a1.setHeaderText(null);;
	  a1.showAndWait();	
   }
   //function to set the side bar buttons to restart, play against CPU, play another player or switch turns
   public VBox getVBox()
   {
      Button restart = new Button("Restart");
      restart.setStyle("-fx-text-fill: rgb(190,0,0)");
      Button cpu = new Button("Play CPU");
      Button player = new Button("Play a Human");
      Button switchT = new Button("Switch Player turns");
      VBox vBox = new VBox(15);
      vBox.setPrefWidth(200);
      vBox.setPadding(new Insets(15, 15, 15, 15));
      vBox.setStyle("-fx-background-color: rgb(44, 130, 201)");
      vBox.getChildren().add(restart);
      vBox.getChildren().add(cpu);
      vBox.getChildren().add(player);
      vBox.getChildren().add(switchT);
      restart.setOnAction(new EventHandler<ActionEvent>()
      {
         public void handle(ActionEvent e) 
         {
            if(((Button)e.getSource()).getText().equals("Restart")) 
            {
               reset();
               if(switchTurns % 2 == 0 && playCPU == true && turn == 1)
               {
                  smartCPU();
               }
            }
         }
      });
      cpu.setOnAction(new EventHandler<ActionEvent>()
      {
         public void handle(ActionEvent e)
         {
            if(((Button)e.getSource()).getText().equals("Play CPU"))
            {
               Alert newOpponent = new Alert(Alert.AlertType.INFORMATION);
               newOpponent.setTitle("Versus");
               newOpponent.setContentText("New Opponent Incoming! Player1 vs. CPU!!!");
               newOpponent.setHeaderText(null);;
               newOpponent.showAndWait();
               playCpuOption();
               if(switchTurns % 2 == 0 && playCPU == true && turn == 1)
               {
                  smartCPU();
               }
            }
         }
      });
      player.setOnAction(new EventHandler<ActionEvent>()
      {
         public void handle(ActionEvent e)
         {
            if(((Button)e.getSource()).getText().equals("Play a Human"))
            {
               Alert newOpponent = new Alert(Alert.AlertType.INFORMATION);
               newOpponent.setTitle("Versus");
               newOpponent.setContentText("New Opponent Incoming! Player1 vs. Player2!!!");
               newOpponent.setHeaderText(null);;
               newOpponent.showAndWait();
               playAnotherPlayerOption();
            }
         }
      });
      switchT.setOnAction(new EventHandler<ActionEvent>()
      {
         public void handle(ActionEvent e)
         {
            if(((Button)e.getSource()).getText().equals("Switch Player turns"))
            {
               Alert switchSides = new Alert(Alert.AlertType.INFORMATION);
               switchSides.setTitle("Switching Turns");
               switchSides.setContentText("SWITCHING SIDES");
               switchSides.setHeaderText(null);;
               switchSides.showAndWait();
               switchPlayerTurn();
               if(switchTurns % 2 == 0 && playCPU == true && turn == 1)
               {
                  smartCPU();
               }
            }
         }
      });
      return vBox;
   }
   //function to check grid input combinations for the winning conditions
   public boolean winnerCheck()
   {
      //row check for winner
      if(array[0][0] == array[0][1] && array[0][0] == array[0][2]) return true;
      else if(array[1][0] == array[1][1] && array[1][0] == array[1][2]) return true;
      else if(array[2][0] == array[2][1] && array[2][0] == array[2][2]) return true;
      //column check for winner
      else if(array[0][0] == array[1][0] && array[0][0] == array[2][0]) return true;
      else if(array[0][1] == array[1][1] && array[0][1] == array[2][1]) return true;
      else if(array[0][2] == array[1][2] && array[0][2] == array[2][2]) return true;
      //diagonal check for winner
      else if(array[0][0] == array[1][1] && array[0][0] == array[2][2]) return true;
      else if(array[0][2] == array[1][1] && array[0][2] == array[2][0]) return true;
      else return false;
   }
   //function that resets the Grid for a new match
   public void reset() 
   {
      counter = 1;
      turn = 1;
      cpuWinCheck = false;
      gameOver = false;
      for(int r = 0; r < 3; r++)
      {
         for(int c = 0; c < 3; c++)
         {
            counter++;
        	array[r][c] = 3 + counter;
         }
      }
      for(int resetr2 = 0; resetr2 < 3; resetr2++)
      {
         for(int resetc2 = 0; resetc2 < 3; resetc2++)
    	 {
    	    btn[resetr2][resetc2].setText("-");
    	 }
      }
   }
   //function to switch to play against a cpu
   public void playCpuOption()
   {
      playCPU = true;
   }
   //function to player another human
   public void playAnotherPlayerOption()
   {
      playCPU = false;
   }
   public void switchPlayerTurn()
   {
      switchTurns++;
      if((switchTurns % 2 == 0) && (playCPU == false))
      {
         Alert a1 = new Alert(Alert.AlertType.INFORMATION);
    	 a1.setTitle("Switching Turns");
    	 a1.setContentText("Player 2 Goes First! Player 1 Goes Second!");
    	 a1.setHeaderText(null);;
    	 a1.showAndWait();
      }
      if((switchTurns % 2 == 1) && (playCPU == false))
      {
         Alert a1 = new Alert(Alert.AlertType.INFORMATION);
    	 a1.setTitle("Switching Turns");
    	 a1.setContentText("Player 1 Goes First! Player 2 Goes Second!");
    	 a1.setHeaderText(null);;
    	 a1.showAndWait();
      }
      if((switchTurns % 2 == 0) && (playCPU == true))
      {
         Alert a1 = new Alert(Alert.AlertType.INFORMATION);
         a1.setTitle("Switching Turns");
         a1.setContentText("CPU Goes First! Player 1 Goes Second!");
    	 a1.setHeaderText(null);;
    	 a1.showAndWait();
      }
      if((switchTurns % 2 == 1) && (playCPU == true))
      {
         Alert a1 = new Alert(Alert.AlertType.INFORMATION);
    	 a1.setTitle("Switching Turns");
    	 a1.setContentText("Player 1 Goes First! CPU Goes Second!");
    	 a1.setHeaderText(null);;
    	 a1.showAndWait();
      }
   }
   //function for CPU play moves
   public void smartCPU() 
   {
      //cpu's first move
      while(((turn == 2) && (playCPU == true) && switchTurns % 2 == 1) || ((turn == 1) && (switchTurns % 2 == 0) && (playCPU == true))) 
      {
         int rand = 1 + rDraw.nextInt(18);
         if(rand == 1 && btn[0][0].getText().equals("-")) 
         {
            btn[0][0].setText("O");
            btn[0][0].setFont(Font.font("Verdana", 40));
            array[0][0] = 2;
            turn++;
         }
         if(rand == 2 && btn[0][1].getText().equals("-")) 
         {
            btn[0][1].setText("O");
            btn[0][1].setFont(Font.font("Verdana", 40));
            array[0][1] = 2;
            turn++;
         }
         if(rand == 3 && (btn[0][2].getText().equals("-"))) 
         {
            btn[0][2].setText("O");
            btn[0][2].setFont(Font.font("Verdana", 40));
            array[0][2] = 2;
            turn++;
         }
         if(rand == 4 && btn[1][0].getText().equals("-")) 
         {
            btn[1][0].setText("O");
            btn[1][0].setFont(Font.font("Verdana", 40));
            array[1][0] = 2;
            turn++;
         }
         if(rand == 5 && btn[1][1].getText().equals("-")) 
         {
            btn[1][1].setText("O");
            btn[1][1].setFont(Font.font("Verdana", 40));
            array[1][1] = 2;
            turn++;
         }
         if(rand == 6 && btn[1][2].getText().equals("-")) 
         {
            btn[1][2].setText("O");
            btn[1][2].setFont(Font.font("Verdana", 40));
            array[1][2] = 2;
            turn++;
         }
         if(rand == 7 && btn[2][0].getText().equals("-")) 
         {
            btn[2][0].setText("O");
            btn[2][0].setFont(Font.font("Verdana", 40));
            array[2][0] = 2;
            turn++;
         }
         if(rand == 8 && btn[2][1].getText().equals("-")) 
         {
            btn[2][1].setText("O");
            btn[2][1].setFont(Font.font("Verdana", 40));
            array[2][1] = 2;
            turn++;
         }
         if(rand == 9 && btn[2][2].getText().equals("-")) 
         {
            btn[2][2].setText("O");
            btn[2][2].setFont(Font.font("Verdana", 40));
            array[2][2] = 2;
            turn++;
         }
         if(rand > 9)
         {
            btn[1][1].setText("O");
            btn[1][1].setFont(Font.font("Verdana", 40));
            array[1][1] = 2;
            turn++;
         }
      }
      while(((turn % 2 == 0) && (turn > 2) && (turn < 9) && (playCPU == true) && switchTurns % 2 == 1) ||
       	    ((turn % 2 == 1) && (turn > 1) && (turn < 10) && (playCPU == true) && (switchTurns % 2 == 0))) 
      {
         //Button 1 Win Move
         if((btn[0][1].getText().equals("O") && btn[0][2].getText().equals("O")
           	&& btn[0][0].getText().equals("-")) ||
           	(btn[2][0].getText().equals("O") && btn[1][0].getText().equals("O")
           	&& btn[0][0].getText().equals("-")) ||
           	(btn[2][2].getText().equals("O") && btn[1][1].getText().equals("O")
           	&& btn[0][0].getText().equals("-")))
         {
            btn[0][0].setText("O");
            btn[0][0].setFont(Font.font("Verdana", 40));
            array[0][0] = 2;
            turn++;
            if(winnerCheck() == true && btn[0][0].getText().equals("O")) 
            {
               cpuWinCheck = true;
               Alert a1 = new Alert(Alert.AlertType.INFORMATION);
               a1.setTitle("GAME OVER");
               a1.setContentText("CPU WINS!");
               a1.setHeaderText(null);;
               a1.showAndWait();
            }
         }
       	 //Button 2 Win Move
         else if((btn[0][0].getText().equals("O") && btn[0][2].getText().equals("0")
           	     && btn[0][1].getText().equals("-")) ||
           		 (btn[2][1].getText().equals("O") && btn[1][1].getText().equals("O")
           	     && btn[0][1].getText().equals("-")))
         {
            btn[0][1].setText("O");
            btn[0][1].setFont(Font.font("Verdana", 40));
            array[0][1] = 2;
            turn++;
            if(winnerCheck() == true && btn[0][1].getText().equals("O")) 
            {
               cpuWinCheck = true;
               Alert a1 = new Alert(Alert.AlertType.INFORMATION);
               a1.setTitle("GAME OVER");
               a1.setContentText("CPU WINS!");
               a1.setHeaderText(null);;
               a1.showAndWait();
            }
         }
       	 //Button 3 Win Move
         else if((btn[0][0].getText().equals("O") && btn[0][1].getText().equals("O")
       			 && btn[0][2].getText().equals("-")) ||
       			 (btn[2][2].getText().equals("O") && btn[1][2].getText().equals("O") 
       			 && btn[0][2].getText().equals("-")) || 
       			 (btn[2][0].getText().equals("O") && btn[1][1].getText().equals("O"))
       			 && btn[0][2].getText().equals("-"))
         {
       	    btn[0][2].setText("O");
       		btn[0][2].setFont(Font.font("Verdana", 40));
       		array[0][2] = 2;
       		turn++;
       		if(winnerCheck() == true && btn[0][2].getText().equals("O")) 
       		{
       		   cpuWinCheck = true;
               Alert a1 = new Alert(Alert.AlertType.INFORMATION);
               a1.setTitle("GAME OVER");
               a1.setContentText("CPU WINS!");
               a1.setHeaderText(null);
               a1.showAndWait();
            }
         }
         //Button 4 Win Move
         else if((btn[1][2].getText().equals("O") && btn[1][1].getText().equals("O")
           		 && btn[1][0].getText().equals("-")) ||
           		 (btn[0][0].getText().equals("O") && btn[2][0].getText().equals("O")
           		 && btn[1][0].getText().equals("-")))
         {
            btn[1][0].setText("O");
            btn[1][0].setFont(Font.font("Verdana", 40));
            array[1][0] = 2;
            turn++;
            if(winnerCheck() == true && btn[1][0].getText().equals("O")) 
            {
               cpuWinCheck = true;
               Alert a1 = new Alert(Alert.AlertType.INFORMATION);
               a1.setTitle("GAME OVER");
               a1.setContentText("CPU WINS!");
               a1.setHeaderText(null);;
               a1.showAndWait();
            }
         }
         //Button 5 Win Move
         else if((btn[1][0].getText().equals("O") && btn[1][2].getText().equals("O")
           		 && btn[1][1].getText().equals("-")) ||
           		 (btn[0][1].getText().equals("O") && btn[2][1].getText().equals("O")
           	     && btn[1][1].getText().equals("-")) ||
           		 (btn[0][0].getText().equals("O") && btn[2][2].getText().equals("O")
           	     && btn[1][1].getText().equals("-")) ||
           		 (btn[0][2].getText().equals("O") && btn[2][0].getText().equals("O")
           	     && btn[1][1].getText().equals("-")))
         {
            btn[1][1].setText("O");
            btn[1][1].setFont(Font.font("Verdana", 40));
            array[1][1] = 2;
            turn++;
            if(winnerCheck() == true && btn[1][1].getText().equals("O")) 
            {
               cpuWinCheck = true;
               Alert a1 = new Alert(Alert.AlertType.INFORMATION);
               a1.setTitle("GAME OVER");
               a1.setContentText("CPU WINS!");
               a1.setHeaderText(null);;
               a1.showAndWait();
            }
         }
         //Button 6 Win Move
         else if((btn[1][0].getText().equals("O") && btn[1][1].getText().equals("O")
           		 && btn[1][2].getText().equals("-")) ||
           		 (btn[0][2].getText().equals("O") && btn[2][2].getText().equals("O")
           	     && btn[1][2].getText().equals("-")))
         {
            btn[1][2].setText("O");
            btn[1][2].setFont(Font.font("Verdana", 40));
            array[1][2] = 2;
            turn++;
            if(winnerCheck() == true && btn[1][2].getText().equals("O")) 
            {
               cpuWinCheck = true;
               Alert a1 = new Alert(Alert.AlertType.INFORMATION);
               a1.setTitle("GAME OVER");
               a1.setContentText("CPU WINS!");
               a1.setHeaderText(null);;
               a1.showAndWait();
            }
         }
         //Button 7 Win Move
         else if((btn[2][1].getText().equals("O") && btn[2][2].getText().equals("O")
           		 && btn[2][0].getText().equals("-")) ||
           		 (btn[0][0].getText().equals("O") && btn[1][0].getText().equals("O")
           	     && btn[2][0].getText().equals("-")) ||
           	     (btn[0][2].getText().equals("O") && btn[1][1].getText().equals("O")
           	     && btn[2][0].getText().equals("-")))
         {
            btn[2][0].setText("O");
            btn[2][0].setFont(Font.font("Verdana", 40));
            array[2][0] = 2;
            turn++;
            if(winnerCheck() == true && btn[2][0].getText().equals("O")) 
            {
               cpuWinCheck = true;
               Alert a1 = new Alert(Alert.AlertType.INFORMATION);
               a1.setTitle("GAME OVER");
               a1.setContentText("CPU WINS!");
               a1.setHeaderText(null);;
               a1.showAndWait();
            }
         }
         //Button 8 Win Move
         else if((btn[2][0].getText().equals("O") && btn[2][2].getText().equals("O")
           	     && btn[2][1].getText().equals("-")) ||
           		 (btn[0][1].getText().equals("O") && btn[1][1].getText().equals("O")
           	     && btn[2][1].getText().equals("-")))
         {
            btn[2][1].setText("O");
            btn[2][1].setFont(Font.font("Verdana", 40));
            array[2][1] = 2;
            turn++;
            if(winnerCheck() == true && btn[2][1].getText().equals("O")) 
            {
               cpuWinCheck = true;
               Alert a1 = new Alert(Alert.AlertType.INFORMATION);
               a1.setTitle("GAME OVER");
               a1.setContentText("CPU WINS!");
               a1.setHeaderText(null);;
               a1.showAndWait();
            }
         }
         //Button 9 Win Move
         else if((btn[2][0].getText().equals("O") && btn[2][1].getText().equals("O")
           		 && btn[2][2].getText().equals("-")) ||
           		 (btn[0][2].getText().equals("O") && btn[1][2].getText().equals("O")
           	     && btn[2][2].getText().equals("-")) ||
           	     (btn[0][0].getText().equals("O") && btn[1][1].getText().equals("O")
           	     && btn[2][2].getText().equals("-")))
         {
            btn[2][2].setText("O");
            btn[2][2].setFont(Font.font("Verdana", 40));
            array[2][2] = 2;
            turn++;
            if(winnerCheck() == true && btn[2][2].getText().equals("O")) 
            {
               cpuWinCheck = true;
               Alert a1 = new Alert(Alert.AlertType.INFORMATION);
               a1.setTitle("GAME OVER");
               a1.setContentText("CPU WINS!");
               a1.setHeaderText(null);;
               a1.showAndWait();
            }
         }	
         //Button 1 Block Move
         else if((btn[0][1].getText().equals("X") && btn[0][2].getText().equals("X")
           	     && btn[0][0].getText().equals("-")) ||
           		 (btn[2][0].getText().equals("X") && btn[1][0].getText().equals("X")
           	     && btn[0][0].getText().equals("-")) ||
           	     (btn[2][2].getText().equals("X") && btn[1][1].getText().equals("X")
           	     && btn[0][0].getText().equals("-")))
         {
            btn[0][0].setText("O");
            btn[0][0].setFont(Font.font("Verdana", 40));
            array[0][0] = 2;
            turn++;
         }
         //Button 2 Block Move
         else if((btn[0][0].getText().equals("X") && btn[0][2].getText().equals("X")
           		 && btn[0][1].getText().equals("-")) ||
           		 (btn[2][1].getText().equals("X") && btn[1][1].getText().equals("X")
       	       	 && btn[0][1].getText().equals("-")))
         {
            btn[0][1].setText("O");
           	btn[0][1].setFont(Font.font("Verdana", 40));
           	array[0][1] = 2;
           	turn++;
         }
         //Button 3 Block Move
         else if((btn[0][0].getText().equals("X") && btn[0][1].getText().equals("X")
           	     && btn[0][2].getText().equals("-")) ||
           		 (btn[2][2].getText().equals("X") && btn[1][2].getText().equals("X")
           	     && btn[0][2].getText().equals("-")) ||
           		 (btn[2][0].getText().equals("X") && btn[1][1].getText().equals("X")
           		 && btn[0][2].getText().equals("-")))
         {
            btn[0][2].setText("O");
            btn[0][2].setFont(Font.font("Verdana", 40));
            array[0][2] = 2;
            turn++;
         }
         //Button 4 Block move
         else if((btn[1][2].getText().equals("X") && btn[1][1].getText().equals("X")
           	     && btn[1][0].getText().equals("-")) ||
           		 (btn[0][0].getText().equals("X") && btn[2][0].getText().equals("X")
           		 && btn[1][0].getText().equals("-")))
         {
            btn[1][0].setText("O");
            btn[1][0].setFont(Font.font("Verdana", 40));
            array[1][0] = 2;
            turn++;
         }
         //Button 5 Block move
         else if((btn[1][0].getText().equals("X") && btn[1][2].getText().equals("X")
           	     && btn[1][1].getText().equals("-")) ||
           		 (btn[0][1].getText().equals("X") && btn[2][1].getText().equals("X")
           	     && btn[1][1].getText().equals("-")) ||
           		 (btn[0][0].getText().equals("X") && btn[2][2].getText().equals("X")
           	     && btn[1][1].getText().equals("-")) ||
           		 (btn[0][2].getText().equals("X") && btn[2][0].getText().equals("X")
           	     && btn[1][1].getText().equals("-")))
         {
            btn[1][1].setText("O");
            btn[1][1].setFont(Font.font("Verdana", 40));
            array[1][1] = 2;
            turn++;
         }
         //Button 6 Block move
         else if((btn[1][0].getText().equals("X") && btn[1][1].getText().equals("X")
           	     && btn[1][2].getText().equals("-")) ||
           	  	 (btn[0][2].getText().equals("X") && btn[2][2].getText().equals("X")
           	     && btn[1][2].getText().equals("-")))
         {
            btn[1][2].setText("O");
            btn[1][2].setFont(Font.font("Verdana", 40));
            array[1][2] = 2;
            turn++;
         }
         //Button 7 Block move
         else if((btn[2][1].getText().equals("X") && btn[2][2].getText().equals("X")
           	     && btn[2][0].getText().equals("-")) ||
           	     (btn[0][0].getText().equals("X") && btn[1][0].getText().equals("X")
           	     && btn[2][0].getText().equals("-")) ||
           	     (btn[0][2].getText().equals("X") && btn[1][1].getText().equals("X")
           	     && btn[2][0].getText().equals("-")))
         {
            btn[2][0].setText("O");
            btn[2][0].setFont(Font.font("Verdana", 40));
            array[2][0] = 2;
            turn++;
         }
         //Button 8 Block move
         else if((btn[2][0].getText().equals("X") && btn[2][2].getText().equals("X")
           	     && btn[2][1].getText().equals("-")) ||
           		 (btn[0][1].getText().equals("X") && btn[1][1].getText().equals("X")
           	     && btn[2][1].getText().equals("-")))
         {
            btn[2][1].setText("O");
            btn[2][1].setFont(Font.font("Verdana", 40));
            array[2][1] = 2;
            turn++;
         }
         //Button 9 Block move
         else if((btn[2][0].getText().equals("X") && btn[2][1].getText().equals("X")
           		 && btn[2][2].getText().equals("-")) ||
           		 (btn[0][2].getText().equals("X") && btn[1][2].getText().equals("X")
           	     && btn[2][2].getText().equals("-")) ||
           		 (btn[0][0].getText().equals("X") && btn[1][1].getText().equals("X")
           	     && btn[2][2].getText().equals("-")))
         {
            btn[2][2].setText("O");
            btn[2][2].setFont(Font.font("Verdana", 40));
            array[2][2] = 2;
            turn++;
         }
         else 
         {
            int rand = 1 + rDraw.nextInt(9);
            if(rand == 1 && btn[0][0].getText().equals("-")) 
            {
               btn[0][0].setText("O");
               btn[0][0].setFont(Font.font("Verdana", 40));
               array[0][0] = 2;
               turn++;
               if(winnerCheck() == true && btn[0][0].getText().equals("O")) 
               {
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                  a1.setTitle("GAME OVER");
                  a1.setContentText("CPU WINS!");
                  a1.setHeaderText(null);;
                  a1.showAndWait();
               }
            }
            if(rand == 2 && btn[0][1].getText().equals("-")) 
            {
               btn[0][1].setText("O");
               btn[0][1].setFont(Font.font("Verdana", 40));
               array[0][1] = 2;
               turn++;
               if(winnerCheck() == true && btn[0][1].getText().equals("O")) 
               {
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                  a1.setTitle("GAME OVER");
                  a1.setContentText("CPU WINS!");
                  a1.setHeaderText(null);;
                  a1.showAndWait();
               }
            }
            if(rand == 3 && (btn[0][2].getText().equals("-"))) 
            {
               btn[0][2].setText("O");
               btn[0][2].setFont(Font.font("Verdana", 40));
               array[0][2] = 2;
               turn++;
               if(winnerCheck() == true && btn[0][2].getText().equals("O")) 
               {
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                  a1.setTitle("GAME OVER");
                  a1.setContentText("CPU WINS!");
                  a1.setHeaderText(null);;
                  a1.showAndWait();
               }
            }
            if(rand == 4 && btn[1][0].getText().equals("-")) 
            {
               btn[1][0].setText("O");
               btn[1][0].setFont(Font.font("Verdana", 40));
               array[1][0] = 2;
               turn++;
               if(winnerCheck() == true && btn[1][0].getText().equals("O")) 
               {
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                  a1.setTitle("GAME OVER");
                  a1.setContentText("CPU WINS!");
                  a1.setHeaderText(null);;
                  a1.showAndWait();
               }
            }
            if(rand == 5 && btn[1][1].getText().equals("-")) 
            {
               btn[1][1].setText("O");
               btn[1][1].setFont(Font.font("Verdana", 40));
               array[1][1] = 2;
               turn++;
               if(winnerCheck() == true && btn[1][1].getText().equals("O")) 
               {
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                  a1.setTitle("GAME OVER");
                  a1.setContentText("CPU WINS!");
                  a1.setHeaderText(null);;
                  a1.showAndWait();
               }
            }
            if(rand == 6 && btn[1][2].getText().equals("-")) 
            {
               btn[1][2].setText("O");
               btn[1][2].setFont(Font.font("Verdana", 40));
               array[1][2] = 2;
               turn++;
               if(winnerCheck() == true && btn[1][2].getText().equals("O")) 
               {
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                  a1.setTitle("GAME OVER");
                  a1.setContentText("CPU WINS!");
                  a1.setHeaderText(null);;
                  a1.showAndWait();
               }
            }
            if(rand == 7 && btn[2][0].getText().equals("-")) 
            {
               btn[2][0].setText("O");
               btn[2][0].setFont(Font.font("Verdana", 40));
               array[2][0] = 2;
               turn++;
               if(winnerCheck() == true && btn[2][0].getText().equals("O")) 
               {
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                  a1.setTitle("GAME OVER");
                  a1.setContentText("CPU WINS!");
                  a1.setHeaderText(null);;
                  a1.showAndWait();
               }
            }
            if(rand == 8 && btn[2][1].getText().equals("-")) 
            {
               btn[2][1].setText("O");
               btn[2][1].setFont(Font.font("Verdana", 40));
               array[2][1] = 2;
               turn++;
               if(winnerCheck() == true && btn[2][1].getText().equals("O")) 
               {
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                  a1.setTitle("GAME OVER");
                  a1.setContentText("CPU WINS!");
                  a1.setHeaderText(null);;
                  a1.showAndWait();
               }
            }
            if(rand == 9 && btn[2][2].getText().equals("-")) 
            {
               btn[2][2].setText("O");
               btn[2][2].setFont(Font.font("Verdana", 40));
               array[2][2] = 2;
               turn++;
               if(winnerCheck() == true && btn[2][2].getText().equals("O")) 
               {
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                  a1.setTitle("GAME OVER");
                  a1.setContentText("CPU WINS!");
                  a1.setHeaderText(null);;
                  a1.showAndWait();
               }
            }
         }
      }
   }
   public static void main(String[] args)
   {
      Application.launch(args);
   }
}
