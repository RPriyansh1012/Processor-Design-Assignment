// Datapath.java
// When we run an instruction, it goes through
//  >Read from register file
//  >MUX picks the right values
//  >ALU does the operation
//  >Write result back to register file

public class Datapath
{
    // all components
    RegisterFile regFile;
    ALU          alu;
    ControlUnit  controlUnit;

    // create all the parts
    public Datapath()
    {
        regFile     = new RegisterFile();
        alu         = new ALU();
        controlUnit = new ControlUnit();
    }

    // load starting values into registers
    public void loadInputs(int a, int b, int c, int d)
    {
        regFile.loadInitialValues(a, b, c, d);
    }

    // run one instruction through the whole datapath (single cycle)
    public void executeInstruction(Instruction instr)
    {
        System.out.println("Executing: " + instr.name);
        //FETCH
        instr.printInstruction();

        //DECODE
        controlUnit.decode(instr);
        controlUnit.printControlSignals();

        //EXECUTE
        int valA = regFile.readRegister(instr.srcReg1);
        int valB = regFile.readRegister(instr.srcReg2);

        System.out.println("  Read Values:");
        System.out.println("    t" + instr.srcReg1 + " = " + valA);
        System.out.println("    t" + instr.srcReg2 + " = " + valB);

        //MUX
        int muxOutA = valA; // mux output A
        int muxOutB = valB; // mux output B

        // ALU doing operation
        int aluResult = alu.doOperation(muxOutA, muxOutB, controlUnit.aluOperation, controlUnit.invertInputA);

        System.out.println("  ALU Result = " + aluResult);

        //WRITE BACK
        regFile.writeRegister(instr.destReg, aluResult, controlUnit.regWriteEnable);

        System.out.println("  Wrote " + aluResult + " into t" + instr.destReg);
        System.out.println();

        // show register file after this instruction
        regFile.printRegisters();
        System.out.println();
    }

    // print all registers
    public void printRegisters()
    {
        regFile.printRegisters();
    }
}
