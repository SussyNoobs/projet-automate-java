public class Automate1D {
    public static int getNewState(String state){

        switch(state){
            case "110":
            case "101":
            case "011":
            case "010":
            case "001":
                return 1;
            case "111":
            case "100":
            case "000":
                return 0;
        }

        return -1;
    }

    public static void main(String[] args) {
        int rule = 111;
        int array[] = new int[10];
        int newArray[] = new int[10];
        array[5] = 1;
        String ruleInBinary = Integer.toBinaryString(rule);
        newArray = array.clone();
        for(int j = 0; j < 10 ; j++){
            System.out.print(newArray[j]);
        }
        System.out.println("");
        for(int i =0 ; i < 5 ; i++){
            System.out.println("before : ");
            for(int j = 0; j < 10 ; j++){
                System.out.print(newArray[j]);
            }
            //System.out.println("");
            array = newArray.clone();
            for(int j = 0; j < 10 ; j++){
                int elem = array[j];
                int before,after;
                if(j==0){
                    before = array[9];
                    after = array[j+1];
                }else if(j==9){
                    before = array[j-1];
                    after = array[0];
                }else{
                    before = array[j-1];
                    after = array[j+1];
                }
                newArray[j] = getNewState(before+""+elem+""+after);
                //System.out.println(before+""+elem+""+after+" devinet : "+newArray[j]);
            }
            //System.out.println("after : ");
            for(int j = 0; j < 10 ; j++){
                System.out.print(newArray[j]);
            }
            System.out.println("");
        }

    }
}
