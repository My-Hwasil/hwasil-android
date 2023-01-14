package com.dev.myHwasil.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dev.myHwasil.R


@Composable
fun HomeScreen(navController: NavController) {
    SelectOption();
}

@Composable
fun SelectOption() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    "성별을 골라주시면\n" + "가실 수 있는 화실을\n" + "보여드릴게요",
                    fontWeight = FontWeight(700),
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Image(
                            modifier = Modifier
                                .width(140.dp)
                                .height(140.dp),
                            painter = painterResource(id = R.drawable.man),
                            contentDescription = "man"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            modifier = Modifier
                                .width(140.dp)
                                .height(140.dp),
                            painter = painterResource(id = R.drawable.girl),
                            contentDescription = "girl"
                        )

                    }
                };
            }
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.BottomEnd) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(16.dp))


                ) {
                    Text("다음", color = Color.White);
                }
            }

        }
    }


}