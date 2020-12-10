package com.github.nsc.de.shake.parser.node.loops;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class WhileNode implements Node {

    private final Tree body;
    private final ValuedNode condition;


    public WhileNode(Tree body, ValuedNode condition) {
        this.body = body;
        this.condition = condition;
    }

    public Tree getBody() {
        return body;
    }

    public ValuedNode getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "WhileNode{" +
                "body=" + body +
                ", condition=" + condition +
                '}';
    }
}
