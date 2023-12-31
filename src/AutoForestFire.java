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

    public static void copyMatrix(int matrix[][],int newMatrix[][]){
        for(int i =0 ; i < 10 ; i++)
            newMatrix[i] = matrix[i].clone();
    }

    public static void printMatrix(int matrix[][],int taille){
        for(int i =0 ; i < taille ; i++){
            for(int j =0 ; j < taille ; j++){
                if(matrix[i][j] == 0)
                    System.out.print("VI ");
                else if(matrix[i][j] == 1)
                    System.out.print("FO ");
                else if(matrix[i][j] == 2)
                    System.out.print("FE ");
                else if(matrix[i][j] == 3)
                    System.out.print("BR ");
            }
            System.out.println(" ");
        }
        System.out.println("");
        System.out.println("");System.out.println("");
    }
    public static void main(String[] args) {
        int matrix[][] = new int[10][10];
        int newMatrix[][] = new int[10][10];
        for(int k =0 ; k < 10 ; k++){
            for(int j =0 ; j < 10 ; j++){
                matrix[k][j] = 0 + (int)(Math.random() * (4 - 0));
            }
        }
        copyMatrix(matrix,newMatrix);

        printMatrix(matrix,10);

        for(int l = 0 ; l < 5 ; l++){
            copyMatrix(newMatrix,matrix);
            for(int i =0 ; i < 10 ; i++){
                for(int j =0 ; j < 10 ; j++){
                    if(matrix[i][j] == 0){
                        newMatrix[i][j] = 0;
                    }else if(matrix[i][j] == 3){
                        newMatrix[i][j] = 3;
                    }else if(matrix[i][j] == 2){
                        newMatrix[i][j] = 3;
                    }else if(voisinEnFeu(matrix,i,j) && matrix[i][j] == 1){
                        //System.out.println(i+"   "+j);
                        newMatrix[i][j] = 2;
                    }else{
                        newMatrix[i][j] = 1;
                    }

                }
            }
            printMatrix(newMatrix,10);
        }
    }
}
