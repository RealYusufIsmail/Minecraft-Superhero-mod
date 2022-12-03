/*
 * Copyright 2022 RealYusufIsmail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
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
package io.github.realyusufismail.config

import com.electronwill.nightconfig.core.file.CommentedFileConfig
import java.nio.file.Path
import java.util.function.Function
import net.minecraftforge.fml.ModContainer
import net.minecraftforge.fml.config.ConfigFileTypeHandler
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.loading.FMLPaths

class SuperHeroModConfig(container: ModContainer, config: IConfig) :
    ModConfig(
        config.configType,
        config.configSpec,
        container,
        "${container.modId}/${config.fileName}.toml") {
    override fun getHandler(): ConfigFileTypeHandler = SuperHeroModFileTypeHandler

    private object SuperHeroModFileTypeHandler : ConfigFileTypeHandler() {
        private fun redirectPath(path: Path) =
            if (path.endsWith("serverconfig")) FMLPaths.CONFIGDIR.get() else path

        override fun reader(configBasePath: Path): Function<ModConfig, CommentedFileConfig> =
            super.reader(redirectPath(configBasePath))

        override fun unload(configBasePath: Path, config: ModConfig) =
            super.unload(redirectPath(configBasePath), config)
    }
}
