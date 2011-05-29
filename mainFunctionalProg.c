//functional programming approach using c/c++

float addition(float number_1, float number_2)
{
  float result;
  result=number_1 + number_2;
  return result;
}

float subtraction(float number_1, float number_2)
{
  float result;
  result=number_1 - number_2;   
  return result;
}


float division(float dividend, float denominator)
{
	float result;

	if(denominator == 0)
	{
		cout<<"Division by 0!";
		return -1;
	}

	
	else
	{
		result = dividend/denominator;
		return result;
	}
}

float mult(float num_1,float num_2)
{
  return num_1*num_2;
}