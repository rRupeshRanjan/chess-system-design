import domain.Board;

import java.util.Arrays;
import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        Scanner sc = new Scanner(System.in);

        System.out.println("input pos in format a,b,c,d - meaning move from index a,b to c,d");

        while (sc.hasNext()) {
            String input = sc.nextLine();
            int[] positions = Arrays.stream(input.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] start = new int[]{positions[0], positions[1]};
            int[] end = new int[]{positions[2], positions[3]};

            System.out.printf("Moving from  %d,%d to %d,%d - ", start[0], start[1], end[0], end[1]);
            System.out.println(board.updateBoard(start, end));
        }
    }
}
