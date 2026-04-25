// ALU.java
// It does AND and OR operations
// It also inverts (NOT) one of the inputs before doing the operation

public class ALU
{
    // 0 = AND, 1 = OR
    public static final int OP_AND = 0;
    public static final int OP_OR  = 1;

    // inputA - first operand
    // inputB - second operand
    // operation - AND or OR
    // invertA - if true, flip all bits of inputA before using it
    public int doOperation(int inputA, int inputB, int operation, boolean invertA)
    {
        int actualA = inputA;

        // if invertA is on, flip all bits of A
        if (invertA == true)
        {
            actualA = ~inputA;

        }

        int result = 0;

        // pick the operation
        if (operation == OP_AND)
        {
            // bitwise AND
            result = actualA & inputB;
        }
        else if (operation == OP_OR)
        {
            // bitwise OR
            result = actualA | inputB;
        }
        else
        {
            System.out.println("ERROR: unknown ALU operation " + operation);
            result = 0;
        }

        return result;
    }
}
