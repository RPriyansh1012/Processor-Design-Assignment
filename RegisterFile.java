// RegisterFile.java
// This holds all of our registers (like little storage boxes)
// We have 8 registers: t0 through t7

public class RegisterFile
{
    // 8 registers, each holds a 32-bit integer
    private int[] registers;

    // constructor - set everything to 0 at start
    public RegisterFile()
    {
        registers = new int[8];
        for (int i = 0; i < 8; i++)
        {
            registers[i] = 0;
        }
    }

    // read a value from a register
    public int readRegister(int regNum)
    {
        // make sure register number is valid
        if (regNum < 0 || regNum > 7)
        {
            System.out.println("ERROR: bad register number " + regNum);
            return 0;
        }
        return registers[regNum];
    }

    // write a value into a register (only if writeEnable is true)
    public void writeRegister(int regNum, int value, boolean writeEnable)
    {
        if (writeEnable == false)
        {
            // dont write if write enable is off
            return;
        }

        if (regNum < 0 || regNum > 7)
        {
            System.out.println("ERROR: bad register number " + regNum);
            return;
        }

        registers[regNum] = value;
    }

    // load initial values into registers before running the program
    public void loadInitialValues(int a, int b, int c, int d)
    {
        // t0 = A, t1 = B, t2 = C, t3 = D
        registers[0] = a;
        registers[1] = b;
        registers[2] = c;
        registers[3] = d;
    }

    // just print all register values so we can see whats going on
    public void printRegisters()
    {
        System.out.println("  Register File State:");
        for (int i = 0; i < 8; i++)
        {
            System.out.println("    t" + i + " = " + registers[i]);
        }
    }
}
