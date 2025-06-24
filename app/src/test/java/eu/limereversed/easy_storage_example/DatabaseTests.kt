package eu.limereversed.easy_storage_example

//import android.content.Context
//import androidx.room.Room
//import eu.limereversed.easy_storage_example.product.Product
//import org.junit.After
//import org.junit.Test
//
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.runner.RunWith
//import java.io.IOException
//import kotlin.jvm.java
//import kotlin.system.measureTimeMillis

//@RunWith(AndroidJUnit4::class)
//class SimpleEntityReadWriteTest {
//    private lateinit var userDao: UserDao
//    private lateinit var db: TestDatabase
//
//    @Before
//    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(
//            context, TestDatabase::class.java).build()
//        userDao = db.getUserDao()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun writeUserAndReadInList() {
//        val user: User = TestUtil.createUser(3).apply {
//            setName("george")
//        }
//        userDao.insert(user)
//        val byName = userDao.findUsersByName("george")
//        assertThat(byName.get(0), equalTo(user))
//    }
//}