package com.quiz;


import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;


public class Quiz{

    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);

        //Intro

        System.out.print("Welcome to the space quiz program.\n");
        System.out.print("Press Y to proceed. N to exit.\n");


        char c = reader.next().charAt(0);
        if(c== 'Y' | c=='y'){

            //Generates the array with question objects

            questionGen generator = new questionGen();
            generator.gen();

            //Initializes the array of question numbers.

            boolean start=true;
            int maxnum = 4; // Sets the number of questions
            ArrayList<Integer> asked = new ArrayList<Integer>();


            //Creates a random number lower than maxnum

            Random rand = new Random();
            int count = rand.nextInt(maxnum);

            //Makes sure that questions once asked are not asked again. 20 is just an arbitrary number of tries.

            while(start){

                if(asked.contains(count)){
                    break;
                }
                start = generator.askquestion(count);
                asked.add(count);
                count = rand.nextInt(maxnum);
                int tries=0;
                while(asked.contains(count) & tries <20){
                    count = rand.nextInt(maxnum);
                    tries+=1;
                }
                if(tries==19) {
                    break;
                }
            }

            System.out.print("\nGame over. You got all the questions correct!\n");
            System.exit(0);



        }
        else{
            System.out.print("Exiting..");
            System.exit(0);
        }
    }
}

//Class for questions

class Questions{
    String text;
    char answer;
    int qnum;


    Questions(String text,char answer, int qnum){
        this.text = text;
        this.answer = answer;
        this.qnum = qnum;
    }

}



class questionGen {

    //ArrayList of question objects

    ArrayList<Questions> question_array = new ArrayList<>();


    //Method that populates the array with questions. Could alternatively be loaded from CSV

    void gen() {

        String text = "How far away is Proxima Centauri, the nearest known star, from Earth? \n A) 4.24 LIGHT YEARS \n B) 12.4 LIGHT YEARS \n C) 10 LIGHT YEARS \n D) 25.9 LIGHT YEARS";
        char answer = 'A';
        int qnum = 1;
        question_array.add(new Questions(text, answer, qnum));

        String text1 = "When viewed from the Earth, Mars seems red due to what? \n A) Atmospheric absorption \n B) Iron oxide in its soil \n C) Parallax Shift \n D) Light scattering";
        char answer1 = 'B';
        int qnum1 = 2;
        question_array.add(new Questions(text1, answer1, qnum1));

        String text2 = "What are Alcor and Mizar? \n A) Stars in the Big Dipper \n B) Cosmic Background Radiation \n C) Stellar Fusion Products \n D) The hunting dogs of Hercules";
        char answer2 = 'A';
        int qnum2 = 3;
        question_array.add(new Questions(text2, answer2, qnum2));

        String text3 = "What is the approximate age of the universe? \n A) 79.3 billion years \n B) 13.8 billion years \n C) 7.2 billion years \n D) 158.1 billion years";
        char answer3 = 'B';
        int qnum3 = 4;
        question_array.add(new Questions(text3, answer3, qnum3));
    }


    //Method that asks the questions.

    boolean askquestion(int count) {

        Questions question = question_array.get(count);

        System.out.print(question.text + '\n');
        Scanner reader = new Scanner(System.in);
        char input = reader.next().charAt(0);

        if (input == question.answer) {
            System.out.print("You are correct.\n");
            return true;

        } else {

            System.out.print("Game Over. You are incorrect.\n");
            System.exit(0);
            return false;
        }

    }

}



