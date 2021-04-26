# Warehousing-And-Sales
This system includes warehousing and sales.

report service:


Models:

	Report:
		id:             Long
		code:           Long
		name:		String
		createBy:	String
		createDate:     DATE, default: Date.now


APIs:

	1-
		@route		GET api/warehouse/availability
		@access		Public
		@res		[{code, nameProduct, count}]

	2-
		@route		POST api/warehouse/availabilityByDate
		@access		Public
		@req		{incomingRemittanceDate, outcomingRemittanceDate}
		@res		[{code, nameProduct, count, incomingRemittanceDate, outcomingRemittanceDate}]

	3-
		@route		GET api/order/payment
		@access		Public
		@res		[{code, nameProduct, count, paymentStatus, date}]

	4-
		@route		GET api/order/forwarding
		@access		Public
		@res		[{code, nameProduct, count, unitCost, destination, deliveryType, transportationCost, finalCost, date}]
