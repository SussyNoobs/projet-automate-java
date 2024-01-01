import java.util.HashMap;
import java.util.Scanner;

public class Automate1D {

    public static int[] generateAnArrayFromString(String initialArray){
        int array[] = new int[initialArray.length()];
        int i = 0;
        for(char c : initialArray.toCharArray()){
            array[i] = Integer.parseInt(String.valueOf(c));
            i++;
        }
        return array;
    }
    public static String binaryFromInt(int rule){
        String result = Integer.toBinaryString(rule);
        if(result.length() < 8){
            int length = result.length();
            for(int i = 0 ; i < 8 - length ; i++){
                System.out.println(result);
                result = "0"+result;
            }
        }
        return result;
    }

    public static HashMap<String,Integer> initializeConfig(String binary){
        HashMap<String,Integer> myMap = new HashMap<String,Integer>();


        myMap.put("111",Integer.parseInt(String.valueOf(binary.toCharArray()[0])));
        myMap.put("110",Integer.parseInt(String.valueOf(binary.toCharArray()[1])));
        myMap.put("101",Integer.parseInt(String.valueOf(binary.toCharArray()[2])));
        myMap.put("100",Integer.parseInt(String.valueOf(binary.toCharArray()[3])));
        myMap.put("011",Integer.parseInt(String.valueOf(binary.toCharArray()[4])));
        myMap.put("010",Integer.parseInt(String.valueOf(binary.toCharArray()[5])));
        myMap.put("001",Integer.parseInt(String.valueOf(binary.toCharArray()[6])));
        myMap.put("000",Integer.parseInt(String.valueOf(binary.toCharArray()[7])));

        return myMap;
    }

    public static int getNewState(HashMap<String,Integer> myMap,String state){

        return myMap.get(state);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Saisir le tableau initiale : ");
        String initialArray = scanner.nextLine();

        System.out.println("Saisir la règle : ");
        int rule = scanner.nextInt();

        System.out.println("Saisir le nombre de génération à simuler : ");
        int generationNb = scanner.nextInt();

        HashMap<String,Integer> myMap = initializeConfig(binaryFromInt(rule));

        int array[] = generateAnArrayFromString(initialArray);
        int newArray[] = array.clone();
        int length = array.length;
        for(int j = 0; j < length; j++){
            System.out.print(newArray[j]);
        }
        System.out.println("");
        for(int i =0 ; i < generationNb ; i++){

            //System.out.println("");
            array = newArray.clone();
            for(int j = 0; j < length; j++){
                int elem = array[j];
                int before,after;
                if(j==0){
                    before = array[length - 1];
                    after = array[j+1];
                }else if(j==length - 1){
                    before = array[j-1];
                    after = array[0];
                }else{
                    before = array[j-1];
                    after = array[j+1];
                }
                newArray[j] = getNewState(myMap,before+""+elem+""+after);
            }
            //System.out.println("after : ");
            for(int j = 0; j < length; j++){
                System.out.print(newArray[j]);
            }
            System.out.println("");
        }

    }
}
