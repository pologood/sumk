/**
 * Copyright (C) 2016 - 2017 youtongluan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yx.conf;

import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.yx.log.Log;
import org.yx.util.CollectionUtils;

public abstract class PropertiesInfo implements FileHandler {

	protected String fileName;

	public PropertiesInfo(String fileName) {
		super();
		this.fileName = fileName;
		FileMonitor.inst.addHandle(this);
	}

	Map<String, String> pro = new HashMap<>();

	public String get(String key) {
		return pro.get(key);
	}

	abstract String get(String key, String defaultValue);

	@Override
	public URL[] listFile() {
		URL url = PropertiesInfo.class.getClassLoader().getResource(fileName);
		if (url == null) {
			Log.get(PropertiesInfo.class).info("{} cannot found", fileName);
			return null;
		}
		return new URL[] { url };
	}

	@Override
	public void deal(InputStream in) throws Exception {
		if (in == null) {
			return;
		}
		pro = Collections.unmodifiableMap(CollectionUtils.loadMap(in));
	}

}
