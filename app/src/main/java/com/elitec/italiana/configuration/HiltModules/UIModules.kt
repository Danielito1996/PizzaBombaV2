package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.presentation.ScreenAdapters.FontAdapter
import com.elitec.italiana.presentation.ScreenAdapters.IFontAdapter
import com.elitec.italiana.presentation.UIHelpers.IScreenSizeHelper
import com.elitec.italiana.presentation.UIHelpers.ScreenSizeHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UIModules {
	@Binds
	@Singleton
	abstract fun bindsScreenSize(
		screenSize:ScreenSizeHelper
	):IScreenSizeHelper

	@Binds
	@Singleton
	abstract  fun bindFontAdapter(
		fontAdapter: FontAdapter
	):IFontAdapter
}