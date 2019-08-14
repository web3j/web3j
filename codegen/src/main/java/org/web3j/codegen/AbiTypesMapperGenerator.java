/*
 * Copyright 2019 Web3 Labs LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.codegen;

import java.io.IOException;
import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Bytes;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Fixed;
import org.web3j.abi.datatypes.Int;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Ufixed;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.primitive.Byte;
import org.web3j.abi.datatypes.primitive.Char;
import org.web3j.abi.datatypes.primitive.Double;
import org.web3j.abi.datatypes.primitive.Long;
import org.web3j.abi.datatypes.primitive.Short;

/** Generator class for creating all the different numeric type variants. */
public class AbiTypesMapperGenerator extends Generator {

    private static final String TYPE = "type";

    public static void main(String[] args) throws Exception {
        AbiTypesMapperGenerator abiTypesMapperGenerator = new AbiTypesMapperGenerator();
        if (args.length == 1) {
            abiTypesMapperGenerator.generate(args[0]);
        } else {
            abiTypesMapperGenerator.generate(
                    System.getProperty("user.dir") + "/core/src/main/java/");
        }
    }

    private void generate(String destinationDir) throws IOException {

        String typesPackageName = "org.web3j.abi.datatypes";
        String primitiveTypesPackageName = "org.web3j.abi.datatypes.primitive";
        String autoGeneratedTypesPackageName = typesPackageName + ".generated";

        MethodSpec getTypeSpec =
                getTypeMethodSpec(
                        typesPackageName, primitiveTypesPackageName, autoGeneratedTypesPackageName);

        MethodSpec getTypeAsStringSpec = getTypeAsStringMethodSpec();
        MethodSpec constructorSpec =
                MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE).build();

        TypeSpec typeSpec =
                TypeSpec.classBuilder("AbiTypes")
                        .addJavadoc(buildWarning(AbiTypesMapperGenerator.class))
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                        .addMethod(constructorSpec)
                        .addMethod(getTypeSpec)
                        .addMethod(getTypeAsStringSpec)
                        .build();

        write(autoGeneratedTypesPackageName, typeSpec, destinationDir);
    }

    private MethodSpec getTypeMethodSpec(
            final String typesPackageName,
            final String primitiveTypesPackageName,
            final String autoGeneratedTypesPackageName) {

        MethodSpec.Builder builder =
                MethodSpec.methodBuilder("getType")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(String.class, TYPE)
                        .returns(
                                ParameterizedTypeName.get(
                                        ClassName.get(Class.class),
                                        WildcardTypeName.subtypeOf(Type.class)))
                        .beginControlFlow("switch (type)");

        builder = addTypes(builder, typesPackageName);
        builder = addPrimitiveTypes(builder, primitiveTypesPackageName);
        builder = addGeneratedTypes(builder, autoGeneratedTypesPackageName);
        builder =
                builder.addStatement(
                        "default:\nthrow new $T($S\n+ $N)",
                        UnsupportedOperationException.class,
                        "Unsupported type encountered: ",
                        TYPE);
        builder.endControlFlow();

        return builder.build();
    }

    private MethodSpec.Builder addTypes(MethodSpec.Builder builder, String packageName) {
        builder =
                addStatement(
                        builder, packageName, Address.TYPE_NAME, Address.class.getSimpleName());

        builder = addStatement(builder, packageName, Bool.TYPE_NAME, Bool.class.getSimpleName());

        builder =
                addStatement(
                        builder,
                        packageName,
                        Boolean.class.getSimpleName().toLowerCase(),
                        Bool.class.getSimpleName());

        builder =
                addStatement(
                        builder,
                        packageName,
                        Utf8String.TYPE_NAME,
                        Utf8String.class.getSimpleName());

        builder =
                addStatement(
                        builder,
                        packageName,
                        DynamicBytes.TYPE_NAME,
                        DynamicBytes.class.getSimpleName());

        // TODO: Fixed array & dynamic array support
        return builder;
    }

    private MethodSpec.Builder addPrimitiveTypes(MethodSpec.Builder builder, String packageName) {

        builder =
                addStatement(
                        builder,
                        packageName,
                        Byte.class.getSimpleName().toLowerCase(),
                        Byte.class.getSimpleName());

        builder =
                addStatement(
                        builder,
                        packageName,
                        Char.class.getSimpleName().toLowerCase(),
                        Char.class.getSimpleName());

        builder =
                addStatement(
                        builder,
                        packageName,
                        Double.class.getSimpleName().toLowerCase(),
                        Double.class.getSimpleName());

        builder =
                addStatement(
                        builder,
                        packageName,
                        Float.class.getSimpleName().toLowerCase(),
                        Float.class.getSimpleName());

        builder =
                addStatement(
                        builder,
                        packageName,
                        org.web3j.abi.datatypes.primitive.Int.class.getSimpleName().toLowerCase(),
                        org.web3j.abi.datatypes.primitive.Int.class.getSimpleName());

        builder =
                addStatement(
                        builder,
                        packageName,
                        Long.class.getSimpleName().toLowerCase(),
                        Long.class.getSimpleName());

        builder =
                addStatement(
                        builder,
                        packageName,
                        Short.class.getSimpleName().toLowerCase(),
                        Short.class.getSimpleName());

        return builder;
    }

    private MethodSpec.Builder addGeneratedTypes(MethodSpec.Builder builder, String packageName) {

        builder = generateIntTypes(builder, packageName);

        // TODO: Enable once Solidity supports fixed types - see
        // https://github.com/ethereum/solidity/issues/409
        // builder = generateFixedTypes(builder, packageName);

        builder = generateFixedBytesTypes(builder, packageName);

        return builder;
    }

    private MethodSpec.Builder generateIntTypes(MethodSpec.Builder builder, String packageName) {
        for (int bitSize = 8; bitSize <= Type.MAX_BIT_LENGTH; bitSize += 8) {

            builder =
                    addStatement(
                            builder,
                            packageName,
                            Uint.TYPE_NAME + bitSize,
                            Uint.class.getSimpleName() + bitSize);
            builder =
                    addStatement(
                            builder,
                            packageName,
                            Int.TYPE_NAME + bitSize,
                            Int.class.getSimpleName() + bitSize);
        }
        return builder;
    }

    private MethodSpec.Builder generateFixedTypes(MethodSpec.Builder builder, String packageName) {
        for (int mBitSize = 8, nBitSize = Type.MAX_BIT_LENGTH - 8;
                mBitSize < Type.MAX_BIT_LENGTH && nBitSize > 0;
                mBitSize += 8, nBitSize -= 8) {
            String suffix = mBitSize + "x" + nBitSize;
            builder =
                    addStatement(
                            builder,
                            packageName,
                            Ufixed.TYPE_NAME + suffix,
                            Ufixed.class.getSimpleName() + suffix);
            builder =
                    addStatement(
                            builder,
                            packageName,
                            Fixed.TYPE_NAME + suffix,
                            Fixed.class.getSimpleName() + suffix);
        }
        return builder;
    }

    private MethodSpec.Builder generateFixedBytesTypes(
            MethodSpec.Builder builder, String packageName) {
        for (int byteSize = 1; byteSize <= 32; byteSize++) {
            builder =
                    addStatement(
                            builder,
                            packageName,
                            Bytes.TYPE_NAME + byteSize,
                            Bytes.class.getSimpleName() + byteSize);
        }
        return builder;
    }

    private MethodSpec.Builder addStatement(
            MethodSpec.Builder builder, String packageName, String typeName, String className) {
        return builder.addStatement(
                "case \"$L\":\nreturn $T.class", typeName, ClassName.get(packageName, className));
    }

    private MethodSpec getTypeAsStringMethodSpec() {

        String controlCondition = "if ($T.class.equals(" + TYPE + "))";

        MethodSpec.Builder builder =
                MethodSpec.methodBuilder("getTypeAString")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(
                                ParameterizedTypeName.get(
                                        ClassName.get(Class.class),
                                        WildcardTypeName.subtypeOf(Type.class)),
                                TYPE)
                        .returns(String.class)
                        .beginControlFlow(controlCondition, Utf8String.class)
                        .addStatement("return \"string\"")
                        .nextControlFlow("else " + controlCondition, DynamicBytes.class)
                        .addStatement("return \"bytes\"")
                        .nextControlFlow("else")
                        .addStatement("return type.getSimpleName().toLowerCase()")
                        .endControlFlow();

        return builder.build();
    }
}
