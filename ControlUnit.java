// ControlUnit.java
// This deduces out what the ALU should do, and whether to invert an input

public class ControlUnit
{
    //output control signals
    boolean regWriteEnable; 
    int     aluOperation;   
    boolean invertInputA;  

    // constructor
    public ControlUnit()
    {
        regWriteEnable = false;
        aluOperation   = 0;
        invertInputA   = false;
    }

    // decode the instruction and set control signals
    public void decode(Instruction instr)
    {
        // reset signals first
        regWriteEnable = false;
        aluOperation   = ALU.OP_AND;
        invertInputA   = false;

        if (instr.opcode == Instruction.OPCODE_RTYPE)
        {
            regWriteEnable = true;

            // settinh ALU signals
            if (instr.funcField == Instruction.FUNC_AND)
            {
                // normal AND
                aluOperation = ALU.OP_AND;
                invertInputA = false;
            }
            else if (instr.funcField == Instruction.FUNC_OR)
            {
                // normal OR
                aluOperation = ALU.OP_OR;
                invertInputA = false;
            }
            else if (instr.funcField == Instruction.FUNC_AND_NOT_A)
            {
                // AND but first input gets inverted
                aluOperation = ALU.OP_AND;
                invertInputA = true;
            }
            else
            {
                System.out.println("ERROR: unknown function field " + instr.funcField);
            }
        }
        else
        {
            System.out.println("ERROR: unknown opcode " + instr.opcode);
        }
    }

    // prints current control signals
    public void printControlSignals()
    {
        System.out.println("  Control Signals:");
        System.out.println("    regWriteEnable = " + regWriteEnable);
        System.out.println("    aluOperation   = " + (aluOperation == ALU.OP_AND ? "AND" : "OR"));
        System.out.println("    invertInputA   = " + invertInputA);
    }
}
