public class AutoForestFire {
    public static boolean voisinEnFeu(int matrix[][],int i,int j){
        if(i == 0 && j == 0){
            return matrix[i][j+1] == 2 || matrix[i+1][j] == 2;
        }else if(i == 0 && j == 9){
            return matrix[i][j-1] == 2 || matrix[i+1][j] == 2;
        }else if(i == 0){
            return matrix[i][j-1] == 2 ||matrix[i][j+1] == 2 || matrix[i+1][j] == 2;
        }else if(j == 0 && i == 9){
            return matrix[i][j+1] == 2 || matrix[i-1][j] == 2;
        }else if(j == 9 && i == 9){
            return matrix[i][j-1] == 2 || matrix[i-1][j] == 2;
        }else if(i == 9){
            //System.out.println(i + " "+ j);
            //System.out.println(matrix[i][j-1] + " " +matrix[i][j+1] +" "+ matrix[i-1][j]);
            return matrix[i][j-1] == 2 ||matrix[i][j+1] == 2 || matrix[i-1][j] == 2;
        }else if(j == 9){
            return matrix[i][j-1] == 2 ||matrix[i-1][j] == 2 || matrix[i+1][j] == 2;
        }else if(j == 0){
            return matrix[i][j+1] == 2 ||matrix[i-1][j] == 2 || matrix[i+1][j] == 2;
        }else{
            return matrix[i][j+1] == 2 || matrix[i+1][j] == 2 || matrix[i][j-1] == 2 || matrix[i-1][j] == 2;
        }
    }



    public static void main(String[] args) {
        int matrix[][] = new int[10][10];
        int newMatrix[][] = new int[10][10];
        for(int k =0 ; k < 10 ; k++){
            for(int j =0 ; j < 10 ; j++){
                matrix[k][j] = 0 + (int)(Math.random() * (4 - 0));
            }
        }
    }
}
