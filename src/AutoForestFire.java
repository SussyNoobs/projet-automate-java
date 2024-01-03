import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AutoForestFire {
    enum STATE {
        EMPTY,
        FOREST,
        FIRE,
        BURNT
    };

    public static int neighboursOnFire(STATE matrix[][], int i,int j){
        int nb = 0;
        if(i - 1 >= 0 && matrix[i-1][j] == STATE.FIRE)
            nb++;
        if(j - 1 >= 0 && matrix[i][j-1] == STATE.FIRE)
            nb++;
        if(i + 1 < matrix.length && matrix[i+1][j] == STATE.FIRE)
            nb++;
        if(j + 1 < matrix.length && matrix[i][j+1] == STATE.FIRE)
            nb++;
        return nb;
    }

    public static boolean shouldBeOnFire(STATE matrix[][],int i,int j,int p,int q){

        int neighboursOnFire = neighboursOnFire(matrix,i,j);
        Random random = new Random();
        return (q + p*neighboursOnFire) > random.nextInt(100);
        /*
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
            return matrix[i][j-1] == 2 ||matrix[i][j+1] == 2 || matrix[i-1][j] == 2;
        }else if(j == 9){
            return matrix[i][j-1] == 2 ||matrix[i-1][j] == 2 || matrix[i+1][j] == 2;
        }else if(j == 0){
            return matrix[i][j+1] == 2 ||matrix[i-1][j] == 2 || matrix[i+1][j] == 2;
        }else{
            return matrix[i][j+1] == 2 || matrix[i+1][j] == 2 || matrix[i][j-1] == 2 || matrix[i-1][j] == 2;
        }*/
    }

    public static void copyMatrix(STATE matrix[][],STATE newMatrix[][]){
        for(int i =0 ; i < matrix.length ; i++)
            newMatrix[i] = matrix[i].clone();
    }

    public static void printMatrix(STATE matrix[][]){
        for(int i =0 ; i < matrix.length ; i++){
            for(int j =0 ; j < matrix.length ; j++){
                if(matrix[i][j] == STATE.EMPTY)
                    System.out.print("VI ");
                else if(matrix[i][j] == STATE.FOREST)
                    System.out.print("FO ");
                else if(matrix[i][j] == STATE.FIRE)
                    System.out.print("FE ");
                else if(matrix[i][j] == STATE.BURNT)
                    System.out.print("BR ");
            }
            System.out.println(" ");
        }
        System.out.println("");
        System.out.println("");System.out.println("");
    }

    public static STATE[][] generateMatrix(int taille,int forestProportion,int fireProportion){
        STATE[][] matrix = new STATE[taille][taille];
        for(int i = 0; i < taille; i++)
            Arrays.fill(matrix[i],STATE.EMPTY);
        Random random = new Random();
        int nbForest = ( forestProportion * (taille * taille ) ) / 100;
        while(nbForest > 0){
            if(matrix[random.nextInt(taille)][random.nextInt(taille)] == STATE.EMPTY){
                matrix[random.nextInt(taille)][random.nextInt(taille)] = STATE.FOREST;
                nbForest--;
            }
        }

        int nbFire = ( fireProportion * (taille * taille ) ) / 100;
        while(nbFire > 0){
            if(matrix[random.nextInt(taille)][random.nextInt(taille)] == STATE.EMPTY){
                matrix[random.nextInt(taille)][random.nextInt(taille)] = STATE.FIRE;
                nbFire--;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisir la proportion de la foret : (pourcentage entre 10 et 80)");
        int forestProportion = scanner.nextInt();

        System.out.println("Saisir la proportion du feu : (pourcentage entre 1 et 50)");
        int fireProportion = scanner.nextInt();

        System.out.println("Saisir la probabilité de propagation du feu p : ");
        int p = scanner.nextInt();

        System.out.println("Saisir la probabilité q (que une cellule foret passe en feu meme si aucune de ses voisines n'est en feu) : ");
        int q = scanner.nextInt();

        System.out.println("Saisir la taille de la matrice : ");
        int taille = scanner.nextInt();

        System.out.println("Saisir le nombre de génération à simuler : ");
        int generationNb = scanner.nextInt();



        STATE matrix[][] = generateMatrix(taille,forestProportion,fireProportion);
        STATE newMatrix[][] = new STATE[taille][taille];

        copyMatrix(matrix,newMatrix);

        printMatrix(matrix);

        for(int l = 0 ; l < generationNb ; l++){
            copyMatrix(newMatrix,matrix);
            for(int i =0 ; i < taille ; i++){
                for(int j =0 ; j < taille ; j++){
                    if(matrix[i][j] == STATE.EMPTY){
                        newMatrix[i][j] = STATE.EMPTY;
                    }else if(matrix[i][j] == STATE.BURNT){
                        newMatrix[i][j] = STATE.BURNT;
                    }else if(matrix[i][j] == STATE.FIRE){
                        newMatrix[i][j] = STATE.BURNT;
                    }else if(shouldBeOnFire(matrix,i,j,p,q)){
                        newMatrix[i][j] = STATE.FIRE;
                    }else{
                        newMatrix[i][j] = STATE.FOREST;
                    }

                }
            }
            printMatrix(newMatrix);
        }
    }
}
