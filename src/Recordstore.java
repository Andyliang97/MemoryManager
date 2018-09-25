/**
 * {Project Description Here}
 */

/**
 * The class containing the main method.
 *
 * @author Junjie Liang and Jie Zhang
 * @version 1.0
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class Recordstore {
    /**
     * @param args Command line parameters
     */
    public static void main(String[] args) {
        // This is the main file for the program.

        int initMemorySize = 0;
        int initHashSize = 0;

        String fileName = "";
        if (args.length != 3) {
            System.out.println("Something Wrong with Parameters");
            return;
        } 
        else {
            initHashSize = Integer.valueOf(args[1]);
            initMemorySize = Integer.valueOf(args[0]);
            fileName = args[2];

        }
        Parser parser = new Parser(initHashSize, initMemorySize);
        parser.readinFile(fileName);

    }
}
