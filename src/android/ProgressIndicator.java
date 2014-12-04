/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */
package jp.watnow.plugins.progress;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

/**
 * This class provides access to notifications on the device.
 *
 * Be aware that this implementation gets called on
 * navigator.notification.{alert|confirm|prompt}, and that there is a separate
 * implementation in org.apache.cordova.CordovaChromeClient that gets called on
 * a simple window.{alert|confirm|prompt}.
 */
public class ProgressIndicator extends CordovaPlugin {

	public int confirmResult = -1;
	public ProgressDialog spinnerDialog = null;
	public ProgressDialog progressDialog = null;
	final static String INPUT_SECURE = "secure";
	final static String INPUT_NORMAL = "normal";

	/**
	 * Constructor.
	 */
	public ProgressIndicator() {
	}

	/**
	 * Executes the request and returns PluginResult.
	 *
	 * @param action
	 *            The action to execute.
	 * @param args
	 *            JSONArray of arguments for the plugin.
	 * @param callbackContext
	 *            The callback context used when calling back into JavaScript.
	 * @return True when the action was valid, false otherwise.
	 */
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		/*
		 * Don't run any of these if the current activity is finishing in order
		 * to avoid android.view.WindowManager$BadTokenException crashing the
		 * app. Just return true here since false should only be returned in the
		 * event of an invalid action.
		 */
		if (this.cordova.getActivity().isFinishing())
			return true;

		if (action.equals("showIndicator")) {
			this.showIndicator(args.getString(0), args.getString(1));
		} else if (action.equals("changeLabels")) {
			this.changeLabels(args.getString(0), args.getString(1));
		} else if (action.equals("showProgress")) {
			this.showProgress(args.getString(0), args.getString(1));
		} else if (action.equals("updateValue")) {
			this.progressValue(args.getInt(0));
		} else if (action.equals("hide")) {
			this.hide();
		} else {
			return false;
		}

		// Only alert and confirm are async.
		callbackContext.success();
		return true;
	}

	// --------------------------------------------------------------------------
	// LOCAL METHODS
	// --------------------------------------------------------------------------

	/**
	 * Show the spinner.
	 *
	 * @param title
	 *            Title of the dialog
	 * @param message
	 *            The message of the dialog
	 */
	public synchronized void showIndicator(final String title,
			final String message) {
		if (this.spinnerDialog != null) {
			this.spinnerDialog.dismiss();
			this.spinnerDialog = null;
		}
		final ProgressIndicator notification = this;
		final CordovaInterface cordova = this.cordova;
		Runnable runnable = new Runnable() {
			public void run() {
				notification.spinnerDialog = createProgressDialog(cordova); // new
																			// ProgressDialog(cordova.getActivity(),
																			// AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
				notification.spinnerDialog.setTitle(title);
				notification.spinnerDialog.setMessage(message);
				notification.spinnerDialog.setCancelable(false);
				notification.spinnerDialog.setIndeterminate(true);
				notification.spinnerDialog
						.setOnCancelListener(new DialogInterface.OnCancelListener() {
							public void onCancel(DialogInterface dialog) {
								notification.spinnerDialog = null;
							}
						});
				notification.spinnerDialog.show();
			}
		};
		this.cordova.getActivity().runOnUiThread(runnable);
	}

	/**
	 * Change Spinner Message.
	 */
	public synchronized void changeLabels(final String title,
			final String message) {
		if (this.spinnerDialog != null) {
			final ProgressIndicator notification = this;
			Runnable runnable = new Runnable() {
				public void run() {
					if (title.length() != 0)
						notification.spinnerDialog.setTitle(title);
					if (message.length() != 0)
						notification.spinnerDialog.setMessage(message);
				}
			};
			this.cordova.getActivity().runOnUiThread(runnable);
		}
	}

	/**
	 * Show the progress dialog.
	 *
	 * @param title
	 *            Title of the dialog
	 * @param message
	 *            The message of the dialog
	 */
	public synchronized void showProgress(final String title,
			final String message) {
		if (this.progressDialog != null) {
			this.progressDialog.dismiss();
			this.progressDialog = null;
		}
		final ProgressIndicator notification = this;
		final CordovaInterface cordova = this.cordova;
		Runnable runnable = new Runnable() {
			public void run() {
				notification.progressDialog = createProgressDialog(cordova); 
				notification.progressDialog
						.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				notification.progressDialog.setTitle(title);
				notification.progressDialog.setMessage(message);
				notification.progressDialog.setCancelable(false);
				notification.progressDialog.setMax(100);
				notification.progressDialog.setProgress(0);
				notification.progressDialog
						.setOnCancelListener(new DialogInterface.OnCancelListener() {
							public void onCancel(DialogInterface dialog) {
								notification.progressDialog = null;
							}
						});
				notification.progressDialog.show();
			}
		};
		this.cordova.getActivity().runOnUiThread(runnable);
	}

	/**
	 * Set value of progress bar.
	 *
	 * @param value
	 *            0-100
	 */
	public synchronized void progressValue(int value) {
		if (this.progressDialog != null) {
			this.progressDialog.setProgress(value);
		}
	}

	/**
	 * Hide ProgressDialog
	 */
	public synchronized void hide() {
		if (this.spinnerDialog != null) {
			this.spinnerDialog.dismiss();
			this.spinnerDialog = null;
		}

		if (this.progressDialog != null) {
			this.progressDialog.dismiss();
			this.progressDialog = null;
		}
	}

	@SuppressLint("InlinedApi")
	private ProgressDialog createProgressDialog(CordovaInterface cordova) {
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return new ProgressDialog(cordova.getActivity(),
					AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		} else {
			return new ProgressDialog(cordova.getActivity());
		}
	}
}
