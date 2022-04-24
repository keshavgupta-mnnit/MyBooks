package com.keshav.mybooks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.keshav.mybooks.model.BookItem
import com.keshav.mybooks.ui.theme.primary
import com.keshav.mybooks.ui.theme.text
import com.keshav.mybooks.ui.theme.typography

@Composable
fun ItemBookList(book: BookItem, onItemClick : () -> Unit) {
    Card(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
            .clickable { onItemClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = book.thumbnailUrl,
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(98.dp, 145.dp)
                    .padding(12.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "by ${book.authors}", style = typography.caption, color = text.copy(0.7f))
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = book.title, style = typography.subtitle1, color = text)
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow {
                    book.categories.forEach {
                        ChipView(it)
                    }
                }

            }
        }
    }
}

@Composable
fun ChipView(category:String) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(primary.copy(.10f))
            .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category, style = typography.caption, color = primary)
    }
}
