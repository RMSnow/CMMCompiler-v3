package v1.ir;

public class Context implements Cloneable {
    CodeChunk chunk;
    VariableRecorder recorder;
    VariablePool variablePool;
    PositionPlaceholder positionPlaceholder;
    JumpStack jumpStack;


    Context() {
        jumpStack = new JumpStack();
        chunk = new CodeChunk();
        recorder = new VariableRecorder();
        variablePool = new VariablePool();
        positionPlaceholder = new PositionPlaceholder();
    }

    private Context(Context context) {
        this.chunk = context.chunk;
        this.positionPlaceholder = context.positionPlaceholder;
        this.variablePool = context.variablePool;
        this.recorder = context.recorder.link();
        this.jumpStack = context.jumpStack;
    }


    Context link() {
        return new Context(this);
    }

    public VariableRecorder getRecorder() {
        return recorder;
    }

    public CodeChunk getChunk() {
        return chunk;
    }

}
