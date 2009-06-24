/*
 * Copyright 2004-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.ui.binding;

import java.util.Map;

import org.springframework.ui.format.AnnotationFormatterFactory;
import org.springframework.ui.format.Formatter;

/**
 * Binds user-entered values to properties of a model object.
 * @author Keith Donald
 * @since 3.0
 * @param <M> The kind of model object this binder binds to
 * @see #configureBinding(BindingConfiguration)
 * @see #bind(UserValues)
 */
public interface Binder {

	/**
	 * The model object this binder binds to.
	 * @return the model object
	 */
	Object getModel();
	
	/**
	 * Configures if this binder is <i>strict</i>; a strict binder requires all bindings to be registered explicitly using {@link #configureBinding(BindingConfiguration)}.
	 * An <i>optimistic</i> binder will implicitly create bindings as required to support {@link #bind(UserValues)} operations.
	 * Default is optimistic.
	 * @param strict strict binder status
	 */
	void setStrict(boolean strict);

	/**
	 * Adds a new binding.
	 * @param configuration the binding configuration
	 * @return the new binding created from the configuration provided
	 */
	Binding configureBinding(BindingConfiguration configuration);

	/**
	 * Adds a Formatter that will format property values of type <code>propertyType</coe>.
	 * @param propertyType the property type
	 * @param formatter the formatter
	 */
	void registerFormatter(Class<?> propertyType, Formatter<?> formatter);

	/**
	 * Adds a AnnotationFormatterFactory that will format values of properties annotated with a specific annotation.
	 * @param factory the annotation formatter factory
	 */
	void registerFormatterFactory(AnnotationFormatterFactory<?, ?> factory);

	/**
	 * Returns the binding for the property.
	 * @param property the property path
	 * @return the binding
	 */
	Binding getBinding(String property);

	/**
	 * Bind values in the map to the properties of the model object.
	 * @param values user-entered values to bind
	 * @return the results of the binding operation
	 */
	BindingResults bind(UserValues values);
	
	/**
	 * Creates a {@link UserValue} list from a Map of user-submitted fields.
	 * The Binder may apply transformations as part of the creation process.
	 * For example, a Binder might insert empty or default values for fields that are not present.
	 * As another example, a Binder might collapse multiple fields into a single {@link UserValue} object.
	 * @param userMap the map of user-submitted fields
	 * @return the UserValue list that can be passed to {@link #bind(UserValues)}.
	 */
	UserValues createUserValues(Map<String, ? extends Object> userMap);

}