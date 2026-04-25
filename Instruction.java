// Instruction.java
// This represents one instruction in our program

public class Instruction
{
    // opcode values 
    public static final int OPCODE_RTYPE = 0;

    // function field values
    // 0 = AND
    // 1 = OR
    // 2 = AND with invert on input A
    public static final int FUNC_AND        = 0;
    public static final int FUNC_OR         = 1;
    public static final int FUNC_AND_NOT_A  = 2; // AND where first source is inverted

    int opcode;
    int destReg;   // which register to write result into
    int srcReg1;   // first source register
    int srcReg2;   // second source register
    int funcField; // tells ALU what to do
    String name;

    // constructor
    public Instruction(int opcode, int destReg, int srcReg1, int srcReg2, int funcField, String name)
    {
        this.opcode   = opcode;
        this.destReg  = destReg;
        this.srcReg1  = srcReg1;
        this.srcReg2  = srcReg2;
        this.funcField = funcField;
        this.name     = name;
    }

    // print the instruction
    public void printInstruction()
    {
        System.out.println("  Instruction: " + name);
        System.out.println("    opcode   = " + opcode);
        System.out.println("    dest     = t" + destReg);
        System.out.println("    src1     = t" + srcReg1);
        System.out.println("    src2     = t" + srcReg2);
        System.out.println("    funcField= " + funcField);
    }
}
