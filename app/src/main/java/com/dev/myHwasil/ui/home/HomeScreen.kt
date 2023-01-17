package com.dev.myHwasil.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dev.myHwasil.R
import com.dev.myHwasil.navigation.Screen


@Composable
fun HomeScreen(navController: NavController) {
    SelectOption(onNavigateToMap = {  navController.navigate(Screen.Map.route) });
}

@Composable
fun SelectOption(onNavigateToMap: () -> Unit) {

    val (isWomanClicked, setIsWomanClicked) = remember { mutableStateOf(false) }
    val (isManClicked, setIsManClicked) = remember { mutableStateOf(false) }
    val (isButtonEnabled, setIsButtonEnabled) = remember { mutableStateOf(false) }

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
                        onClick = {
                            setIsWomanClicked(false); setIsManClicked(true); setIsButtonEnabled(
                            true
                        );
                        },
                    ) {
                        if (isManClicked) Image(
                            modifier = Modifier
                                .width(140.dp)
                                .height(140.dp),
                            painter = painterResource(id = R.drawable.man_clicked),
                            contentDescription = "man"
                        ) else Image(
                            modifier = Modifier
                                .width(140.dp)
                                .height(140.dp),
                            painter = painterResource(id = R.drawable.man),
                            contentDescription = "man"
                        )

                    }
                    IconButton(onClick = {
                        setIsWomanClicked(true); setIsManClicked(false); setIsButtonEnabled(
                        true
                    );
                    }) {

                        if (isWomanClicked) Image(
                            modifier = Modifier
                                .width(140.dp)
                                .height(140.dp),
                            painter = painterResource(id = R.drawable.girl_clicked),
                            contentDescription = "girl"
                        ) else Image(
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
                    enabled = isButtonEnabled,
                    onClick = onNavigateToMap,
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

