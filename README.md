# Single-Cycle 32-Bit Processor Simulator (Java)

A simple simulation of a single-cycle 32-bit processor written in Java. It evaluates the Boolean expression **Y = A·B + C'·D** using a register file, ALU, control unit, and datapath — all modeled as separate files.

The program simulates how a real processor executes instructions one at a time. It runs a small 3-instruction program that computes:

```
Y = A·B + C'·D
```
**(A AND B) OR (NOT C AND D)**

The three instructions it runs are:

```
and t4, t0, t1     →  t4 = A & B
and t6, t5, t3     →  t6 = (~C) & D
or  t0, t4, t6     →  t0 = t4 | t6
```

## File Structure

```
processor/
├── Main.java          # Sets inputs and runs the program
├── Datapath.java      # Runs each instruction
├── ControlUnit.java   # Decodes instructions, generates control signals
├── ALU.java           # Does AND / OR, input inversion (NOT)
├── RegisterFile.java  # Stores register values (t0 through t7)
└── Instruction.java   # Represents a single instruction
```

---

## How to Run

Make sure you have Java installed (JDK 8 or higher).

**Step 1 — Put all 6 files in the same folder.**

**Step 2 — Compile everything:**

**Step 3 — Run Main.java:**

You should see the complete process in the terminal

---

## How to Change the Input Values

Open `Main.java` and change these lines near the top of `main()`:

```java
int A = 1;
int B = 1;
int C = 0;
int D = 1;
```

The program will recompute Y automatically.

## Example Output

With inputs A=1, B=1, C=0, D=1:

```
Input Values:
  A = 1
  B = 1
  C = 0
  D = 1

Executing: and t4, t0, t1   (t4 = A & B)
  Control Signals:
    regWriteEnable = true
    aluOperation   = AND
    invertInputA   = false
  Read Values:
    t0 = 1
    t1 = 1
  ALU Result = 1
  Wrote 1 into t4

Executing: and t6, t5, t3   (t6 = (~C) & D)
  Control Signals:
    regWriteEnable = true
    aluOperation   = AND
    invertInputA   = true
  Read Values:
    t5 = 0
    t3 = 1
  ALU Result = 1
  Wrote 1 into t6

Executing: or  t0, t4, t6   (t0 = t4 | t6 = Y)
  Control Signals:
    regWriteEnable = true
    aluOperation   = OR
    invertInputA   = false
  Read Values:
    t4 = 1
    t6 = 1
  ALU Result = 1
  Wrote 1 into t0

FINAL RESULTS
  t4 = A & B       = 1
  t6 = (~C) & D    = 1
  t0 = Y (final)   = 1
  Expected Y = 1
  PASS: Output matches expected value!
```

---

## Notes

- All registers are 32-bit integers, but for Boolean logic only bit 0 (the least significant bit) matters for the final answer.
- The NOT operation is handled entirely through the ALU's `invertInputA` signal.
- C is stored in both `t2` and `t5` before the program runs. `t5` is the one fed into the second instruction as `src1`, so the ALU can invert it.
- Register `t0` holds the final result Y after the third instruction runs.
