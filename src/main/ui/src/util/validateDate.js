export default function validate(beginDate, endDate) {
  if (beginDate == null || endDate == null) return true;

  const arrStart = beginDate.split('-');
  const arrEnd = endDate.split('-');

  for (let i = 0; i < 3; i += 1) {
    if (arrStart[i] > arrEnd[i]) {
      return false;
    } else if (arrStart[i] < arrEnd[i]) {
      break;
    }
  }

  return true;
}
