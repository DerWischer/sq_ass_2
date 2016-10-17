public void draw(DrawHandler drawHandler, DrawingInfo drawingInfo) {
	double width = drawingInfo.getSymmetricWidth(getFirstLifeline(), getLastLifeline(), tick);
	double height = TextSplitter.getSplitStringHeight(textLines, width - ROUND_PART_WIDTH * 2, drawHandler) + VERTICAL_BORDER_PADDING * 2;
	double topY = drawingInfo.getVerticalStart(tick);
	topY += (drawingInfo.getTickHeight(tick) - height) / 2;
	double leftX = drawingInfo.getHDrawingInfo(getFirstLifeline()).getSymmetricHorizontalStart(tick);

	drawHandler.drawArc(leftX, topY, ROUND_PART_WIDTH * 2, height, 90, 180, true);
	width = width - ROUND_PART_WIDTH * 2;
	drawHandler.drawArc(leftX + width, topY, ROUND_PART_WIDTH * 2, height, 270, 180, true);
	drawHandler.drawLine(leftX + ROUND_PART_WIDTH, topY, leftX + width + ROUND_PART_WIDTH, topY);
	drawHandler.drawLine(leftX + ROUND_PART_WIDTH, topY + height, leftX + width + ROUND_PART_WIDTH, topY + height);
	TextSplitter.drawText(drawHandler, textLines, leftX + ROUND_PART_WIDTH, topY, width, height,
			AlignHorizontal.CENTER, AlignVertical.CENTER);

	for (Lifeline ll : coveredLifelines) {
		drawingInfo.getDrawingInfo(ll).addInterruptedArea(new Line1D(topY, topY + height));
	}
}