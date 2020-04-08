package aditya.sensyne.hospitaluk.data.repository

import aditya.sensyne.hospitaluk.data.api.ApiService
import aditya.sensyne.hospitaluk.data.model.HospitalModel
import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG
import com.opencsv.CSVParser
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.time.Duration

class HospitalRepository(val app: Application) {

    fun getHospitals(): MutableList<HospitalModel> {
        //var retrofitApiService = retrofit.create(ApiService::class.java)
        downloadFile()
        return parseCSV()
    }

    private fun parseCSV(): MutableList<HospitalModel> {
        val csvReader: CSVReader
        val customParser: CSVParser = CSVParserBuilder().withSeparator('�').build()
        val mutableHospitalModel: MutableList<HospitalModel> = mutableListOf()
        try {
            val input = BufferedReader(InputStreamReader(app.assets.open("hospital.csv")))
            csvReader = CSVReaderBuilder(input).withCSVParser(customParser).build()
            var record: Array<String>?
            csvReader.readNext()
            record = csvReader.readNext()
            while (record != null) {
                println(record.asList())
                val hospitalModel =
                    HospitalModel(
                        record[0].toInt(),
                        record[1],
                        record[2],
                        record[3],
                        record[4],
                        record[5],
                        record[6].toBoolean(),
                        record[7],
                        record[8],
                        record[9],
                        record[10],
                        record[11],
                        record[12],
                        record[13],
                        record[14],
                        record[15],
                        record[16],
                        record[17],
                        record[18],
                        record[19],
                        record[20],
                        record[21]
                    )
                mutableHospitalModel.add(hospitalModel)
                record = csvReader.readNext()
            }
            csvReader.close()
            return mutableHospitalModel
        } catch (e: Exception) {
            e.printStackTrace()
            return mutableListOf()
        }

        //   if (mutableHospitalModel != null) {
        //       sharedMainViewModel?.hospitalsList(mutableHospitalModel)
        //   }
    }

    fun downloadFile() {
        Log.d("downloadFile", "start")
        val retrofit = Retrofit.Builder()
            .baseUrl( "http://media.nhschoices.nhs.uk/")
            .client(
                OkHttpClient.Builder().callTimeout(Duration.ofMinutes(1))
                    .connectTimeout(Duration.ofMinutes(1)).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java!!)

        val call = service.getHospitals()

        call.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                try {
                    if (response?.isSuccessful == true) {
                        val execute = object : AsyncTask<Void, Void, Void>() {
                            override fun doInBackground(vararg voids: Void): Void? {
                                val writtenToDisk = writeResponseBodyToDisk(
                                    response.body(),
                                    "hospitals.csv"
                                )
                                Log.d("download", "file download was a success? $writtenToDisk")
                                return null
                            }
                        }.execute()
                    } else {
                        Log.d(TAG, "server contact failed");
                    }

                } catch (e: IOException) {
                    Log.d("onResponse", "There is an error")
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d("onFailure", t.toString())
            }
        })

    }

    private fun writeResponseBodyToDisk(body: ResponseBody?, fileName: String): Boolean {
        try {
            val uri: String = app.getExternalFilesDir(null).toString() + File.separator + fileName
            var retrofitBetaFile =
                File(uri)
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)

                var fileSizeDownloaded: Long = 0

                inputStream = body?.byteStream()
                outputStream = FileOutputStream(retrofitBetaFile)

                while (true) {
                    val read = inputStream!!.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream!!.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                }
                outputStream!!.flush()
                return true
            } catch (e: IOException) {
                return false
            } finally {
                if (inputStream != null) {
                    inputStream!!.close()
                }

                if (outputStream != null) {
                    outputStream!!.close()
                }
            }
        } catch (e: IOException) {
            return false
        }
    }

}