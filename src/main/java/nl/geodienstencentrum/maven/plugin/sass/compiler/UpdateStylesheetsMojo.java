/*
 * Copyright 2014-2015 Mark Prins, GeoDienstenCentrum.
 * Copyright 2010-2014 Jasig.
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.geodienstencentrum.maven.plugin.sass.compiler;

import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_SOURCES;
import nl.geodienstencentrum.maven.plugin.sass.AbstractSassMojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Mojo that compiles Sass sources into CSS files using
 * {@code update_stylesheets}.
 */
@Mojo(name = "update-stylesheets", defaultPhase = PROCESS_SOURCES)
public class UpdateStylesheetsMojo extends AbstractSassMojo {

	/**
	 * Execute the compiler script.
	 *
	 * @see org.apache.maven.plugin.Mojo#execute()
	 * @throws MojoExecutionException when the execution of the plugin
	 *         errored
	 * @throws MojoFailureException when the sass compilation fails
	 *
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (this.isSkip()) {
			return;
		}
		this.getLog().info("Compiling Sass Templates");

		// build sass script
		final StringBuilder sassBuilder = new StringBuilder();
		this.buildBasicSassScript(sassBuilder);
		sassBuilder.append("Sass::Plugin.update_stylesheets");
		final String sassScript = sassBuilder.toString();

		// ...and execute
		this.executeSassScript(sassScript);
	}
}
