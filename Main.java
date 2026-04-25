// Main.java
//  Y = A·B + C'·D
// The program:
//   and t4, t0, t1      --> t4 = A & B
//   and t6, t5, t3      --> t6 = (~C) & D
//   or  t0, t4, t6      --> t0 = t4 | t6 = final Y

public class Main
{
    public static void main(String[] args)
    {
        int A = 1;
        int B = 1;
        int C = 0;
        int D = 1;

        System.out.println("  Single-Cycle 32-bit Processor Sim");
        System.out.println("  Target: Y = A·B + C'·D");
        System.out.println();
        System.out.println("Input Values:");
        System.out.println("  A = " + A);
        System.out.println("  B = " + B);
        System.out.println("  C = " + C);
        System.out.println("  D = " + D);
        System.out.println();

        // create the datapath
        Datapath datapath = new Datapath();

        // load A, B, C, D into t0, t1, t2, t3
        datapath.loadInputs(A, B, C, D);
        datapath.regFile.writeRegister(5, C, true);

        System.out.println("Initial Register State:");
        datapath.printRegisters();
        System.out.println();

        // building the instruction list
        // instruction 1: and t4, t0, t1  --> t4 = A & B
        // opcode = R-type, dest = t4, src1 = t0, src2 = t1, func = FUNC_AND
        Instruction instr1 = new Instruction(
            Instruction.OPCODE_RTYPE,
            4,   // dest = t4
            0,   // src1 = t0 (A)
            1,   // src2 = t1 (B)
            Instruction.FUNC_AND,
            "and t4, t0, t1   (t4 = A & B)"
        );

        // instruction 2: and t6, t5, t3  --> t6 = (~C) & D
        // use FUNC_AND_NOT_A so the ALU inverts src1 (which is t5 = C)
        Instruction instr2 = new Instruction(
            Instruction.OPCODE_RTYPE,
            6,   // dest = t6
            5,   // src1 = t5 (, will be inverted by ALU)
            3,   // src2 = t3 (D)
            Instruction.FUNC_AND_NOT_A,
            "and t6, t5, t3   (t6 = (~C) & D)"
        );

        // instruction 3: or t0, t4, t6  --> t0 = t4 | t6 = Y
        Instruction instr3 = new Instruction(
            Instruction.OPCODE_RTYPE,
            0,   // dest = t0
            4,   // src1 = t4
            6,   // src2 = t6
            Instruction.FUNC_OR,
            "or  t0, t4, t6   (t0 = t4 | t6 = Y)"
        );

        // run the program
        System.out.println("Starting Program Execution...");
        System.out.println();

        datapath.executeInstruction(instr1);
        datapath.executeInstruction(instr2);
        datapath.executeInstruction(instr3);

        // show final result
        int finalY = datapath.regFile.readRegister(0);

        // double checking the answer
        int expectedY = (A & B) | ((~C & 1) & D); 

        System.out.println("FINAL RESULTS");
        System.out.println("  t4 = A & B       = " + datapath.regFile.readRegister(4));
        System.out.println("  t6 = (~C) & D    = " + datapath.regFile.readRegister(6));
        System.out.println("  t0 = Y (final)   = " + (finalY & 1));
        System.out.println("  Expected Y = A·B + C'·D = " + expectedY);

        // checking if it matches
        if ((finalY & 1) == expectedY)
        {
            System.out.println("  PASS: Output matches expected value!");
        }
        else
        {
            System.out.println("  FAIL: Output does NOT match. Something is wrong.");
        }
    }
}
