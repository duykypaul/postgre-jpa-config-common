package com.bezkoder.spring.jpa.postgresql.sqlfileprocessor.generator;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Modifier;
import java.util.List;

public class TypeGenerator implements Generator<TypeSpec> {
    private final String repositoryName;
    private final String entityType; // type of entity in JpaRepository
    private final String keyType;

    private final List<MethodSpec> methods;

    public TypeGenerator(String repositoryName, String entityType, String keyType, List<MethodSpec> methods) {
        this.repositoryName = repositoryName;
        this.entityType = entityType;
        this.keyType = keyType;
        this.methods = methods;
    }

    @Override
    public TypeSpec generate() {
        return TypeSpec
                .interfaceBuilder(repositoryName)
                .addAnnotation(Repository.class)
                .addSuperinterface(ParameterizedTypeName.get(
                        ClassName.get(JpaRepository.class),
                        ClassName.bestGuess(entityType),
                        ClassName.bestGuess(keyType)
                ))
                .addModifiers(Modifier.PUBLIC)
                .addMethods(methods)
                .build();
    }
}
