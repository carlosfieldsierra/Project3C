package codegen.generator;

import java.io.IOException;

import codegen.MipsGenerator;
import ir.*;
import visitor.symbol.MethodSymbol;
import visitor.symbol.Symbol;
import registerallocator.RegisterAllocator;
import syntaxtree.IdentifierType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CopyGenerator {

    private MipsGenerator mipsGenerator;

    public CopyGenerator(MipsGenerator mipsGenerator){
        this.mipsGenerator = mipsGenerator;
    }

    public void generate(Quadruple instruction) throws IOException{
      
    }
}
