package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.ValuedNode;

public abstract class ExpressionNode implements ValuedNode {
    private final ValuedNode left;
    private final ValuedNode right;

    public ExpressionNode(ValuedNode left, ValuedNode right) {
        this.left = left;
        this.right = right;
    }

    public abstract char getOperator();

    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "("+(getLeft() != null && !(getLeft() instanceof NumberNode && ((NumberNode)getLeft()).getNumber() == 0) ? getLeft() : "")+getOperator()+getRight()+")";
    }
}
