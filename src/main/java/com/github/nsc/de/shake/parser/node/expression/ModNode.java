package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class ModNode extends ExpressionNode {
    public ModNode(ValuedNode left, ValuedNode right) {
        super(left, right);
    }

    @Override
    public char getOperator() {
        return '%';
    }
}
