/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
var exec = require('cordova/exec');

module.exports = {
	/**
	 * Indicator
	 * @param title string
	 * @param message string
	 * @param dim boolean
	 */
	showIndicator: function (title, message, dim) {
		var _title = title || "Loading";
		var _message = message || "Please wait...";
		cordova.exec(null, null, "ProgressIndicator", "showIndicator", [_title, _message, false]);
	},

	/**
	 * Change Labels
	 * @param title string
	 * @param message string
	 */
	changeLabels: function (title, message) {
		var _title = title || "";
		var _message = message || "";
		cordova.exec(null, null, "ProgressIndicator", "changeLabels", [_title, _message]);
	},

	/**
	 * Show ProgressBar
	 *
	 * @param title
	 * @param message
	 */
	showProgress: function (title, message) {
		var _title = title || "Loading";
		var _message = message || "Please wait...";
		cordova.exec(null, null, "ProgressIndicator", "showProgress", [_title, _message, false]);
	},

	/**
	 * Update Progress value
	 *
	 * @param value {int} 1-100
	 */
	updateValue: function (value) {
		if (typeof value == 'number') {
			if (value < 1)
				value = 1;
			if (value > 100)
				value = 100;
			cordova.exec(null, null, "ProgressIndicator", "updateValue", [value]);
		}
	},

	/**
	 * hide
	 */
	hide: function () {
		cordova.exec(null, null, "ProgressIndicator", "hide", []);
	}

	/*
	 show: function (type, dim, label, detail) {
	 dim = dim || false;
	 label = label || "Please wait...";
	 detail = detail || '';

	 cordova.exec(null, null, "ProgressIndicator", "show", [type, dim, label, detail]);
	 },


	 showSimple: function (dim) {
	 dim = dim || false;
	 cordova.exec(null, null, "ProgressIndicator", "showSimple", [dim]);

	 },

	 showSimpleWithLabel: function (dim, label) {
	 dim = dim || false;
	 label = label || "Please wait...";

	 cordova.exec(null, null, "ProgressIndicator", "showSimpleWithLabel", [dim, label]);
	 },

	 showSimpleWithLabelDetail: function (dim, label, detail) {
	 dim = dim || false;
	 label = label || "Please wait...";
	 detail = detail || "Loading";

	 cordova.exec(null, null, "ProgressIndicator", "showSimpleWithLabelDetail", [dim, label, detail]);
	 },

	 showDeterminate: function (dim, timeout) {
	 dim = dim || false;
	 timeout = timeout || 50000;
	 cordova.exec(null, null, "ProgressIndicator", "showDeterminate", [dim, timeout]);
	 },

	 showDeterminateWithLabel: function (dim, timeout, label) {
	 dim = dim || false;
	 timeout = timeout || 50000;
	 label = label || "Please wait...";

	 cordova.exec(null, null, "ProgressIndicator", "showDeterminateWithLabel", [dim, timeout, label]);
	 },

	 showAnnular: function (dim, timeout) {
	 dim = dim || false;
	 timeout = timeout || 50000;
	 cordova.exec(null, null, "ProgressIndicator", "showDeterminateAnnular", [dim, timeout]);
	 },

	 showAnnularWithLabel: function (dim, timeout, label) {
	 dim = dim || false;
	 timeout = timeout || 50000;
	 label = label || "Please wait...";

	 cordova.exec(null, null, "ProgressIndicator", "showDeterminateAnnularWithLabel", [dim, timeout, label]);
	 },

	 showBar: function (dim, timeout) {
	 dim = dim || false;
	 timeout = timeout || 50000;

	 cordova.exec(null, null, "ProgressIndicator", "showDeterminateBar", [dim, timeout]);
	 },

	 showBarWithLabel: function (dim, timeout, label) {
	 dim = dim || false;
	 timeout = timeout || 50000;
	 label = label || "Please wait...";

	 cordova.exec(null, null, "ProgressIndicator", "showDeterminateBarWithLabel", [dim, timeout, label]);
	 },


	 showSuccess: function (dim, label) {
	 dim = dim || false;
	 label = label || "Success";

	 cordova.exec(null, null, "ProgressIndicator", "showSuccess", [dim, label]);
	 },

	 showText: function (dim, label, position) {
	 dim = dim || false;
	 label = label || "Success";
	 position = position || "bottom";

	 cordova.exec(null, null, "ProgressIndicator", "showText", [dim, label, position]);
	 },


	 hide: function () {
	 cordova.exec(null, null, "ProgressIndicator", "hide", []);
	 }*/
};