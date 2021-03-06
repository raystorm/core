/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wicketstuff.objectautocomplete.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.wicketstuff.objectautocomplete.AutoCompletionChoicesProvider;
import org.wicketstuff.objectautocomplete.ObjectAutoCompleteBuilder;
import org.wicketstuff.objectautocomplete.ObjectAutoCompleteField;

/**
 * Delay examples
 */
public class DelayExamplePage extends BaseExamplePage<Car, Integer>
{

	private static final long serialVersionUID = 1L;

	public DelayExamplePage()
	{
		super(new Model<Integer>());
	}

	@Override
	List<Car> getAllChoices()
	{
		return CarRepository.allCars();
	}

	@Override
	protected void initBuilder(
		ObjectAutoCompleteBuilder<Car, Integer> pCarIntegerObjectAutoCompleteBuilder)
	{
		pCarIntegerObjectAutoCompleteBuilder.delay(1000);
	}

	@Override
	String getCodeSample()
	{
		return "ObjectAutoCompleteBuilder<Car,Integer> builder =\n"
			+ "       new ObjectAutoCompleteBuilder<Car,Integer>(\n"
			+ "                new AutoCompletionChoicesProvider<Car>() {\n"
			+ "                    public Iterator<Car> getChoices(String input) {\n"
			+ "                       List<Car> ret = new ArrayList<Car>();\n"
			+ "                       for (Car car : CarRepository.allCars()) {\n"
			+ "                           if (car.getName().toLowerCase()\n"
			+ "                                  .startsWith(input.toLowerCase())) {\n"
			+ "                               ret.add(car);\n" + "                            }\n"
			+ "                       }\n" + "                      return ret.iterator();\n"
			+ "                  }\n" + "               }\n" + "       ).delay(1000);\n"
			+ "ObjectAutoCompleteField acField = \n"
			+ "       builder.build(\"acField\", new Model<Integer>());\n" + "form.add(acField);";
	}

	// Used for C&P in the string above
	private void test()
	{
		Form<Void> form = new Form<Void>("form");
		ObjectAutoCompleteBuilder<Car, Integer> builder = new ObjectAutoCompleteBuilder<Car, Integer>(
			new AutoCompletionChoicesProvider<Car>()
			{
				private static final long serialVersionUID = 1L;

				public Iterator<Car> getChoices(String input)
				{
					List<Car> ret = new ArrayList<Car>();
					for (Car car : CarRepository.allCars())
					{
						if (car.getName().toLowerCase().startsWith(input.toLowerCase()))
						{
							ret.add(car);
						}
					}
					return ret.iterator();
				}
			}).delay(1000);
		ObjectAutoCompleteField<Car, Integer> acField = builder.build("acField",
			new Model<Integer>());
		form.add(acField);
	}

	@Override
	String getHtmlSample()
	{
		return "<form wicket:id=\"form\">\n"
			+ " Brand: <input type=\"text\" wicket:id=\"acField\"/>\n" + "</form>";
	}

	@Override
	protected void addIfMatch(List<Car> pCars, Car pCar, String pInput)
	{
		if (pCar.getName().toLowerCase().startsWith(pInput.toLowerCase()))
		{
			pCars.add(pCar);
		}
	}

}