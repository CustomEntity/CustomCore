package fr.customentity.customcore;

import fr.customentity.customcore.common.TestedVersions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PluginInformations {

    TestedVersions[] testedVersion() default TestedVersions.NONE;

}
