//Assignment 2 for CS 1410 - Rubik's Cube

package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Cube {


	static String[][] cube = {{
		"r","r","r",
		"r","r","r",
		"r","r","r"
	},{
		"b","b","b",
		"b","b","b",
		"b","b","b"
	},{
		"o","o","o",
		"o","o","o",
		"o","o","o"
	},{
		"g","g","g",
		"g","g","g",
		"g","g","g"
	},{
		"y","y","y",
		"y","y","y",
		"y","y","y"
	},{
		"w","w","w",
		"w","w","w",
		"w","w","w"
	}};

	public void showCube(){
		for(int i = 0; i < 6; i++){
			int n = 0;
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < 3; k++){
					System.out.print(cube[i][j+k+n]);
				}
				n += 2;
				System.out.println();
			}
			System.out.println();
		}
	}


	class edgeFace{
		public int current_face;
		int[] edge1 = new int[4];//index 0 is the face and 1-3 are the elements of the face
		int[] edge2 = new int[4];
		int[] edge3 = new int[4];
		int[] edge4 = new int[4];

		public edgeFace(int face){
			current_face = face;

			switch(face){
				case 0:
				edge1[0] = 3;
				edge1[1] = 2;
				edge1[2] = 5;
				edge1[3] = 8;

				edge2[0] = 5;
				edge2[1] = 2;
				edge2[2] = 5;
				edge2[3] = 8;

				edge3[0] = 1;
				edge3[1] = 6;
				edge3[2] = 3;
				edge3[3] = 0;

				edge4[0] = 4;
				edge4[1] = 2;
				edge4[2] = 5;
				edge4[3] = 8;
				break;
				case 1:
				edge1[0] = 0;
				edge1[1] = 2;
				edge1[2] = 5;
				edge1[3] = 8;

				edge2[0] = 5;
				edge2[1] = 0;
				edge2[2] = 1;
				edge2[3] = 2;

				edge3[0] = 2;
				edge3[1] = 6;
				edge3[2] = 3;
				edge3[3] = 0;

				edge4[0] = 4;
				edge4[1] = 8;
				edge4[2] = 7;
				edge4[3] = 6;
				break;
				case 2:
				edge1[0] = 1;
				edge1[1] = 8;
				edge1[2] = 5;
				edge1[3] = 2;

				edge2[0] = 5;
				edge2[1] = 0;
				edge2[2] = 3;
				edge2[3] = 6;

				edge3[0] = 3;
				edge3[1] = 0;
				edge3[2] = 3;
				edge3[3] = 6;

				edge4[0] = 4;
				edge4[1] = 0;
				edge4[2] = 3;
				edge4[3] = 6;
				break;
				case 3:
				edge1[0] = 2;
				edge1[1] = 2;
				edge1[2] = 5;
				edge1[3] = 8;

				edge2[0] = 5;
				edge2[1] = 8;
				edge2[2] = 7;
				edge2[3] = 6;

				edge3[0] = 0;
				edge3[1] = 6;
				edge3[2] = 3;
				edge3[3] = 0;

				edge4[0] = 4;
				edge4[1] = 0;
				edge4[2] = 1;
				edge4[3] = 2;
				break;
				case 4:
				edge1[0] = 1;
				edge1[1] = 6;
				edge1[2] = 7;
				edge1[3] = 8;

				edge2[0] = 2;
				edge2[1] = 6;
				edge2[2] = 7;
				edge2[3] = 8;

				edge3[0] = 3;
				edge3[1] = 6;
				edge3[2] = 7;
				edge3[3] = 8;

				edge4[0] = 0;
				edge4[1] = 6;
				edge4[2] = 7;
				edge4[3] = 8;
				break;
				case 5:
				edge1[0] = 3;
				edge1[1] = 0;
				edge1[2] = 1;
				edge1[3] = 2;

				edge2[0] = 2;
				edge2[1] = 0;
				edge2[2] = 1;
				edge2[3] = 2;

				edge3[0] = 1;
				edge3[1] = 0;
				edge3[2] = 1;
				edge3[3] = 2;

				edge4[0] = 0;
				edge4[1] = 0;
				edge4[2] = 1;
				edge4[3] = 2;
				break;
			}
		}
	}

	public void turnFace(int index, String direction){
		edgeFace eFace = new edgeFace(index);

		String[][] copy = new String[6][9];

		//copies the cube by value
		for(int i =0; i < 6; i++){
			for(int j = 0; j < 9; j++){
				copy[i][j] = cube[i][j];
			}
		}

		//turns the face
		switch(direction){
			case"c":
			//After the rotation, current face 0 will be the same as the original face 6, so we need to copy the face 6 to the current face 0.
			//Similar process for the other squares on the face.
			cube[eFace.current_face][0] = copy[eFace.current_face][6];
			cube[eFace.current_face][1] = copy[eFace.current_face][3];
			cube[eFace.current_face][2] = copy[eFace.current_face][0];
			cube[eFace.current_face][3] = copy[eFace.current_face][7];
			cube[eFace.current_face][4] = copy[eFace.current_face][4];//middle, this could be left out since it never changes but I've included it for clarity.
			cube[eFace.current_face][5] = copy[eFace.current_face][1];
			cube[eFace.current_face][6] = copy[eFace.current_face][8];
			cube[eFace.current_face][7] = copy[eFace.current_face][5];
			cube[eFace.current_face][8] = copy[eFace.current_face][2];

			//edge 1
			cube[eFace.edge1[0]][eFace.edge1[1]] = copy[eFace.edge4[0]][eFace.edge4[1]];
			cube[eFace.edge1[0]][eFace.edge1[2]] = copy[eFace.edge4[0]][eFace.edge4[2]];
			cube[eFace.edge1[0]][eFace.edge1[3]] = copy[eFace.edge4[0]][eFace.edge4[3]];

			//edge 2
			cube[eFace.edge2[0]][eFace.edge2[1]] = copy[eFace.edge1[0]][eFace.edge1[1]];
			cube[eFace.edge2[0]][eFace.edge2[2]] = copy[eFace.edge1[0]][eFace.edge1[2]];
			cube[eFace.edge2[0]][eFace.edge2[3]] = copy[eFace.edge1[0]][eFace.edge1[3]];

			//edge 3
			cube[eFace.edge3[0]][eFace.edge3[1]] = copy[eFace.edge2[0]][eFace.edge2[1]];
			cube[eFace.edge3[0]][eFace.edge3[2]] = copy[eFace.edge2[0]][eFace.edge2[2]];
			cube[eFace.edge3[0]][eFace.edge3[3]] = copy[eFace.edge2[0]][eFace.edge2[3]];

			//edge 4
			cube[eFace.edge4[0]][eFace.edge4[1]] = copy[eFace.edge3[0]][eFace.edge3[1]];
			cube[eFace.edge4[0]][eFace.edge4[2]] = copy[eFace.edge3[0]][eFace.edge3[2]];
			cube[eFace.edge4[0]][eFace.edge4[3]] = copy[eFace.edge3[0]][eFace.edge3[3]];

			break;
			case"cc":
			//After the rotation, current face 0 will be the same as the original face 2, so we need to copy the face 2 to the current face 0.
			//Similar process for the other squares on the face.
			cube[eFace.current_face][0] = copy[eFace.current_face][2];
			cube[eFace.current_face][1] = copy[eFace.current_face][5];
			cube[eFace.current_face][2] = copy[eFace.current_face][8];
			cube[eFace.current_face][3] = copy[eFace.current_face][1];
			cube[eFace.current_face][4] = copy[eFace.current_face][4];//middle, this could be left out since it never changes but I've included it for clarity.
			cube[eFace.current_face][5] = copy[eFace.current_face][7];
			cube[eFace.current_face][6] = copy[eFace.current_face][0];
			cube[eFace.current_face][7] = copy[eFace.current_face][3];
			cube[eFace.current_face][8] = copy[eFace.current_face][6];

			//edge 1
			cube[eFace.edge1[0]][eFace.edge1[1]] = copy[eFace.edge2[0]][eFace.edge2[1]];
			cube[eFace.edge1[0]][eFace.edge1[2]] = copy[eFace.edge2[0]][eFace.edge2[2]];
			cube[eFace.edge1[0]][eFace.edge1[3]] = copy[eFace.edge2[0]][eFace.edge2[3]];

			//edge 2
			cube[eFace.edge2[0]][eFace.edge2[1]] = copy[eFace.edge3[0]][eFace.edge3[1]];
			cube[eFace.edge2[0]][eFace.edge2[2]] = copy[eFace.edge3[0]][eFace.edge3[2]];
			cube[eFace.edge2[0]][eFace.edge2[3]] = copy[eFace.edge3[0]][eFace.edge3[3]];

			//edge 3
			cube[eFace.edge3[0]][eFace.edge3[1]] = copy[eFace.edge4[0]][eFace.edge4[1]];
			cube[eFace.edge3[0]][eFace.edge3[2]] = copy[eFace.edge4[0]][eFace.edge4[2]];
			cube[eFace.edge3[0]][eFace.edge3[3]] = copy[eFace.edge4[0]][eFace.edge4[3]];

			//edge 4
			cube[eFace.edge4[0]][eFace.edge4[1]] = copy[eFace.edge1[0]][eFace.edge1[1]];
			cube[eFace.edge4[0]][eFace.edge4[2]] = copy[eFace.edge1[0]][eFace.edge1[2]];
			cube[eFace.edge4[0]][eFace.edge4[3]] = copy[eFace.edge1[0]][eFace.edge1[3]];

			break;
		}
	}
	public static void main(final String[] args)
	throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Cube cube = new Cube();

		ArrayList <String> moves = new ArrayList<String>();

		boolean argsCheck = false;
		int argsRunIndex = 0;

		if(args.length > 0){
			argsCheck = true;
		}
		
		boolean proceed = true;

		while(proceed){
			String input;

			if(!argsCheck){
				input = reader.readLine();
			}
			else{
				if(argsRunIndex == args.length){
					argsCheck = false;
					input ="s";
				}else{
					input = args[argsRunIndex];
					argsRunIndex++;
				}
			}

			switch(input){
				case"u":
				cube.turnFace(5, "c");
				moves.add("u'");
				cube.showCube();
				break;
				case"d":
				cube.turnFace(4, "c");
				moves.add("d'");
				cube.showCube();
				break;
				case"r":
				cube.turnFace(0, "c");
				moves.add("r'");
				cube.showCube();
				break;
				case"l":
				cube.turnFace(2, "c");
				moves.add("l'");
				cube.showCube();
				break;
				case"f":
				cube.turnFace(3, "c");
				moves.add("f'");
				cube.showCube();
				break;
				case"b":
				cube.turnFace(1, "c");
				moves.add("b'");
				cube.showCube();
				break;
				case"u'":
				cube.turnFace(5, "cc");
				moves.add("u");
				cube.showCube();
				break;
				case"d'":
				cube.turnFace(4, "cc");
				moves.add("d");
				cube.showCube();
				break;
				case"r'":
				cube.turnFace(0, "cc");
				moves.add("r");
				cube.showCube();
				break;
				case"l'":
				cube.turnFace(2, "cc");
				moves.add("l");
				cube.showCube();
				break;
				case"f'":
				cube.turnFace(3, "cc");
				moves.add("f");
				cube.showCube();
				break;
				case"b'":
				cube.turnFace(1, "cc");
				moves.add("b");
				cube.showCube();
				break;
				case"s":
				System.out.print("Moves: ");
				for(int i = 1; i < moves.size()+1; i++){
					System.out.print(moves.get(moves.size() - i) + " ");
				}
				break;
				case"q":
				proceed = false;
				break;
			}
		}

		cube.turnFace(0, "c");
		cube.showCube();
		cube.turnFace(0, "cc");
		cube.showCube();
	}
}

